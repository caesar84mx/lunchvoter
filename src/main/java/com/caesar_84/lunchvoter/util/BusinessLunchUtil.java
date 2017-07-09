package com.caesar_84.lunchvoter.util;

import com.caesar_84.lunchvoter.domain.Menu;
import com.caesar_84.lunchvoter.domain.Meal;
import com.caesar_84.lunchvoter.dto.BusinessLunch;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessLunchUtil {
    private BusinessLunchUtil(){}

    /**
     * Returns list of BusinessLunch DTO objects. Make sure that the specified parameters are not null
     * @param menus a menus list
     * @param meals meals list.
     * @return a filtered list of BusinessLunch DTO objects
     */
    public static List<BusinessLunch> getBusinessLunchList(List<Menu> menus, List<Meal> meals) {
        return getFilteredBusinessLunchList(menus, meals, LocalDate.MIN, LocalDate.MAX);
    }

    /**
     * Returns a filtered by date and time list of BusinessLunch DTO objects. Make sure that the specified parameters are
     * not null
     * @param menus a menus list
     * @param meals meals list.
     * @param start starting date and time parameter
     * @param end ending date and time parameter
     * @return a filtered list of BusinessLunch DTO objects
     */
    public static List<BusinessLunch> getFilteredBusinessLunchList(List<Menu> menus, List<Meal> meals, LocalDate start, LocalDate end) {

        return menus.stream()
                .filter(menu -> DateUtil.isBetween(menu.getPublished(), start, end))
                .map(lunch -> {
                    List<Meal> currentMeals = meals.stream()
                            .filter(meal -> meal.getMenu().getId().equals(lunch.getId()))
                            .collect(Collectors.toList());
                    return getBusinessLunch(lunch, currentMeals);
                }).collect(Collectors.toList());
    }

    /**
     * Gets a BusnessLunch DTO object from the specified parameters. Parameters are checked to be not null and the meals
     * list - to belong all its items to the specified meal.
     * @param menu - a Menu object
     * @param meals - list of the menu items (Meals objects). Make sure that the items have the reference to the
     * specified Menu object
     * @return BusnessLunch DTO object
     */
    public static BusinessLunch getBusinessLunch(Menu menu, List<Meal> meals) {
        Assert.notNull(menu, "Menu must not be null");
        Assert.notNull(meals, "Meals list must not be null");
        meals.forEach(meal ->
                Assert.isTrue(meal.getMenu().getId().equals(menu.getId()), "Meal must belong to the menu"));

        return new BusinessLunch(menu.getId(), menu.getRestaurant(), menu.getPublished(), menu.getPrice(), meals);
    }
}
