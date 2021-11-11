package com.lemonado.smartmeet.core.data.models.registration.builders;

import com.lemonado.smartmeet.core.data.models.registration.StartRegistrationModel;

public final class StartRegistrationModelBuilder {
    String registrationCode;
    String email;
    long roleId;

    public StartRegistrationModelBuilder() {
    }


    public StartRegistrationModelBuilder withRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
        return this;
    }

    public StartRegistrationModelBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public StartRegistrationModelBuilder withRoleId(long roleId) {
        this.roleId = roleId;
        return this;
    }

    public StartRegistrationModel build() {
        return new StartRegistrationModel(registrationCode, email, roleId);
    }
}