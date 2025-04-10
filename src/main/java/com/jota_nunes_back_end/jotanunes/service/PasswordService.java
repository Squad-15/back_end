package com.jota_nunes_back_end.jotanunes.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    private static final SecureRandom random = new SecureRandom();

    public String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
}
}
