package com.lemonado.smartmeet.core.data.models.users.builders;

import com.lemonado.smartmeet.core.data.models.users.UserModel;

import java.time.OffsetDateTime;

public final class UserModelBuilder {
        long id;
        String username;
        String email;
        String passwordHash;
        OffsetDateTime deleteTimestamp;
        OffsetDateTime validTokenTimestamp;

        public UserModelBuilder() {
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