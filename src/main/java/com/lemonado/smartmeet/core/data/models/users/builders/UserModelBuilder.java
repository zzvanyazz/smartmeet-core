package com.lemonado.smartmeet.core.data.models.users.builders;

import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.OffsetDateTime;

public final class UserModelBuilder {

    private long id;
    private String username;
    private String email;
    private String passwordHash;
    private OffsetDateTime deleteTimestamp;
    private OffsetDateTime validTokenTimestamp;


    public static UserModelBuilder builder() {
        return new UserModelBuilder();
    }


    public UserModelBuilder withoutId() {
        this.id = 0;
        return this;
    }

    public UserModelBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserModelBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserModelBuilder withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }


    public UserModelBuilder withDeleteTimestamp(OffsetDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
        return this;
    }

    public UserModelBuilder withValidTokenTimestamp(OffsetDateTime validTokenTimestamp) {
        this.validTokenTimestamp = validTokenTimestamp;
        return this;
    }

    public UserModel build() {
        return new UserModel(id,
                username,
                email,
                passwordHash,
                deleteTimestamp,
                validTokenTimestamp);
    }
}