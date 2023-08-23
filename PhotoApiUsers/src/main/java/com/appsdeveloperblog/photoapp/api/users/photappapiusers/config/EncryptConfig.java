package com.appsdeveloperblog.photoapp.api.users.photappapiusers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Configuration
public class EncryptConfig {
    private static final String ENCRYPTION_PASSWORD = "my-secret-key";
    private static final BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);

    public TextEncryptor textEncryptor() {
        String encryptionSalt = keyGenerator.generateKey().toString();
        return Encryptors.text(ENCRYPTION_PASSWORD, encryptionSalt);
    }
}


