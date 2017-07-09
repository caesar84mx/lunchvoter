package com.caesar_84.lunchvoter.service.implementations;

import com.caesar_84.lunchvoter.LoggedUser;
import com.caesar_84.lunchvoter.domain.Menu;
import com.caesar_84.lunchvoter.domain.User;
import com.caesar_84.lunchvoter.domain.Vote;
import com.caesar_84.lunchvoter.repository.MenuRepository;
import com.caesar_84.lunchvoter.repository.UserRepository;
import com.caesar_84.lunchvoter.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VotingService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    private static final LocalTime deadline = LocalTime.of(11,0);

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Vote> vote(int id) {
        logger.info("Voting for menu " + id);
        if(LocalTime.now().isAfter(deadline)) {
            logger.error("Could not vote: deadline expired.");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Vote vote = voteRepository.getForMenuAndUser(id, LoggedUser.id());
        Menu menu;

        if (vote == null) {
            menu = menuRepository.findOne(id);
            User user = userRepository.findOne(LoggedUser.id());
            vote = new Vote(user, menu);
        } else {
            menu = vote.getMenu();
        }

        LocalDate today = LocalDate.now();
        if (!menu.getPublished().equals(today)) {
            logger.error("Could not vote: outdated menu " + id);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
    }
}
