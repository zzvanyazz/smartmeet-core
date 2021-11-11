package com.lemonado.smartmeet.core.data.models.users;


import java.time.OffsetDateTime;


public record UserModel(
        long id,
        String username,
        String email,
        String passwordHash,
        OffsetDateTime deleteTimestamp,
        OffsetDateTime validTokenTimestamp) {

}
