package com.lemonado.smartmeet.core.services.validation.mail;

import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import com.lemonado.smartmeet.core.services.base.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailContentServiceImpl mailContentService;

    @Override
    public void sendRegistrationMessage(String code, String email, RoleModel roleModel) {
        var content = mailContentService.createRegistrationContent(
                roleModel.name(),
                roleModel.description(),
                code);
        mailSender.sendMail(email, "Registration", content);
    }

}
