package com.lemonado.smartmeet.core.data.models.registration;

public record StartRegistrationModel(
        String registrationCode,
        String email,
        long roleId) {

}
