package com.lemonado.smartmeet.core.services.base.mail;

import com.lemonado.smartmeet.core.data.exceptions.CanNotSendMailException;
import com.lemonado.smartmeet.core.data.models.roles.RoleModel;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    void sendRegistrationMessage(String code, String email, RoleModel roleModel) throws CanNotSendMailException;

}
