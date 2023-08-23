package com.appsdeveloperblog.photoapp.api.users.photappapiusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    @Autowired
    private TextEncryptor textEncryptor;

    public String encryptString(String input) {
        return textEncryptor.encrypt(input);
    }

    public String decryptString(String encryptedInput) {
        return textEncryptor.decrypt(encryptedInput);
    }
}
