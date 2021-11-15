package com.lemonado.smartmeet.core.services.base.secure;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface SecureRandomService {

    String generateDigits(int length) throws NoSuchAlgorithmException;

    String generateAlphaNumeric(int length) throws NoSuchAlgorithmException;
}
