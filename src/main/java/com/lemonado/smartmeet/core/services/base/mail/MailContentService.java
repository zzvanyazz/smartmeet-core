package com.lemonado.smartmeet.core.services.base.mail;

import org.springframework.stereotype.Service;

@Service
public interface MailContentService {

    String createRegistrationContent(String roleName, String roleDescription, String registrationCode);

}
