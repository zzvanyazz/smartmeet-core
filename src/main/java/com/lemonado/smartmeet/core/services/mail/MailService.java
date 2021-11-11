package com.lemonado.smartmeet.core.services.mail;

import com.lemonado.smartmeet.core.data.exceptions.CanNotSendMailException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailContentService mailContentService;


    public void sendRegistrationMessage(String code, String email, RoleModel roleModel) throws CanNotSendMailException {
        var content = mailContentService.createRegistrationContent(
                roleModel.name(),
                roleModel.description(),
                code);
        mailSender.sendMail(email, "Registration", content);
    }

}
