package com.lemonado.smartmeet.core.config;

import com.lemonado.smartmeet.core.services.base.groups.GroupService;
import com.lemonado.smartmeet.core.services.base.groups.GroupUsersService;
import com.lemonado.smartmeet.core.services.base.timeline.TimeLineService;
import com.lemonado.smartmeet.core.services.base.users.RegistrationService;
import com.lemonado.smartmeet.core.services.base.users.UserRolesService;
import com.lemonado.smartmeet.core.services.base.users.UserService;
import com.lemonado.smartmeet.core.services.impl.groups.GroupServiceImpl;
import com.lemonado.smartmeet.core.services.impl.groups.GroupUsersServiceImpl;
import com.lemonado.smartmeet.core.services.impl.timeline.TimeLineServiceImpl;
import com.lemonado.smartmeet.core.services.impl.users.RegistrationServiceImpl;
import com.lemonado.smartmeet.core.services.impl.users.UserRolesServiceImpl;
import com.lemonado.smartmeet.core.services.impl.users.UserServiceImpl;
import com.lemonado.smartmeet.core.services.validation.groups.GroupServiceValidation;
import com.lemonado.smartmeet.core.services.validation.groups.GroupUsersServiceValidation;
import com.lemonado.smartmeet.core.services.validation.timeline.TimeLineServiceValidation;
import com.lemonado.smartmeet.core.services.validation.users.RegistrationServiceValidation;
import com.lemonado.smartmeet.core.services.validation.users.UserRolesServiceValidation;
import com.lemonado.smartmeet.core.services.validation.users.UserServiceValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServiceConfigs {

    @Bean
    @Primary
    public GroupService getGroupService() {
        return new GroupServiceValidation(
                new GroupServiceImpl());
    }

    @Bean
    @Primary
    public GroupUsersService getGroupUsersService() {
        return new GroupUsersServiceValidation(
                new GroupUsersServiceImpl());
    }

    @Bean
    @Primary
    public TimeLineService getTimeLineService() {
        return new TimeLineServiceValidation(
                new TimeLineServiceImpl());
    }

    @Bean
    @Primary
    public RegistrationService getRegistrationService() {
        return new RegistrationServiceValidation(
                new RegistrationServiceImpl());
    }

    @Bean
    @Primary
    public UserRolesService getUserRolesService() {
        return new UserRolesServiceValidation(
                new UserRolesServiceImpl());
    }

    @Bean
    @Primary
    public UserService getUserService() {
        return new UserServiceValidation(
                new UserServiceImpl());
    }

}
