package com.caesar_84.lunchvoter.service.implementations;

import com.caesar_84.lunchvoter.domain.Meal;
import com.caesar_84.lunchvoter.domain.Menu;
import com.caesar_84.lunchvoter.domain.Restaurant;
import com.caesar_84.lunchvoter.dto.BusinessLunch;
import com.caesar_84.lunchvoter.repository.MealRepository;
import com.caesar_84.lunchvoter.repository.MenuRepository;
import com.caesar_84.lunchvoter.repository.RestaurantRepository;
import com.caesar_84.lunchvoter.util.BusinessLunchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusinessLunchService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MealRepository mealRepository;

    @Transactional
    public BusinessLunch get(int id) {
        Menu menu = menuRepository.findOne(id);
        List<Meal> meals = mealRepository.getByLunch(id);

        return BusinessLunchUtil.getBusinessLunch(menu, meals);
    }

    @Transactional
    public List<BusinessLunch> getAll() {
        List<Meal> meals = mealRepository.findAll();
        List<Menu> menus = menuRepository.findAll(new Sort(Sort.Direction.DESC,"published", "price"));

        return BusinessLunchUtil.getBusinessLunchList(menus, meals);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public BusinessLunch save(BusinessLunch businessLunch) {
        Restaurant restaurant = restaurantRepository.save(businessLunch.getRestaurant());
        Menu menu = menuRepository.save(new Menu(businessLunch.getId(), restaurant, businessLunch.getPrice(), businessLunch.getPublished()));
        businessLunch.getItems().forEach(item -> item.setMenu(menu));
        mealRepository.save(businessLunch.getItems());

        if (null == businessLunch.getId()) {
            businessLunch.setId(menu.getId());
        }

        return businessLunch;
    }

/*    @Transactional
    @Secured("ROLE_ADMIN")
    @Override
    public void delete(int id) {
        lunchRepository.delete(id);
    }*/

    @Transactional
    public List<BusinessLunch> getBetween(LocalDate start, LocalDate end) {
        List<Meal> meals = mealRepository.findAll();
        List<Menu> menus = menuRepository.getBetween(start, end);

        return BusinessLunchUtil.getFilteredBusinessLunchList(menus, meals, start, end);
    }

    @Transactional
    public List<BusinessLunch> getBy(LocalDate date) {
        List<Menu> menus = menuRepository.getByDate(date);
        List<Meal> meals = mealRepository.findAll();

        return BusinessLunchUtil.getBusinessLunchList(menus, meals);
    }
}