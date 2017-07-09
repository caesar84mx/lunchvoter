package com.caesar_84.lunchvoter.web;

import com.caesar_84.lunchvoter.domain.Vote;
import com.caesar_84.lunchvoter.service.implementations.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    @Autowired
    VotingService service;

    @RequestMapping(value = "/rest-api/v1/vote/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Vote> vote(@PathVariable("id") int id) {

        return service.vote(id);
    }
}