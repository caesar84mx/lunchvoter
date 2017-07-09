package com.caesar_84.lunchvoter.configuration;

import com.caesar_84.lunchvoter.domain.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Configuration that allows to include id in the REST response.
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Menu.class);
        config.exposeIdsFor(Restaurant.class);
        config.exposeIdsFor(Meal.class);
        config.exposeIdsFor(Vote.class);
    }
}
