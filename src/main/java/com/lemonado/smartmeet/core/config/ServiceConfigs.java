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
    public GroupService getGroupService(GroupServiceImpl impl) {
        return new GroupServiceValidation(impl);
    }

    @Bean
    @Primary
    public GroupUsersService getGroupUsersService(GroupUsersServiceImpl impl) {
        return new GroupUsersServiceValidation(impl);
    }

    @Bean
    @Primary
    public TimeLineService getTimeLineService(TimeLineServiceImpl impl) {
        return new TimeLineServiceValidation(impl);
    }

    @Bean
    @Primary
    public RegistrationService getRegistrationService(RegistrationServiceImpl impl) {
        return new RegistrationServiceValidation(impl);
    }

    @Bean
    @Primary
    public UserRolesService getUserRolesService(UserRolesServiceImpl impl) {
        return new UserRolesServiceValidation(impl);
    }

    @Bean
    @Primary
    public UserService getUserService(UserServiceImpl impl) {
        return new UserServiceValidation(impl);
    }

}
