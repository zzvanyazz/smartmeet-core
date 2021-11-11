package com.lemonado.smartmeet.core.options;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecureOptions {

    @Value("${secure.registration.code.length}")
    private int registrationCodeLength;

    @Value("${secure.group.code.length}")
    private int groupCodeLength;

    public int getRegistrationCodeLength() {
        return registrationCodeLength;
    }

    public int getGroupCodeLength() {
        return groupCodeLength;
    }
}
