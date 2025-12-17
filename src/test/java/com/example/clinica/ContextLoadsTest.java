package com.example.clinica;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContextLoadsTest {

    @Test
    void bcryptEncoderProducesMatchingHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String raw = "password123";
        String hash = encoder.encode(raw);
        assertTrue(encoder.matches(raw, hash));
    }
}
