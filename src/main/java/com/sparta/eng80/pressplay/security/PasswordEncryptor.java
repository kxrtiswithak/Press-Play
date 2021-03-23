package com.sparta.eng80.pressplay.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncryptor {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
