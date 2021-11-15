package com.lemonado.smartmeet.core.services.validation.mail;

import com.lemonado.smartmeet.core.services.base.mail.MailContentService;
import org.springframework.stereotype.Service;

@Service
public class MailContentServiceImpl implements MailContentService {

    @Override
    public String createRegistrationContent(String roleName, String roleDescription, String registrationCode) {
        return String.format(
                "Your role: %s (%s).\n Your registration code: %s",
                roleName,
                roleDescription,
                registrationCode);
    }

}
