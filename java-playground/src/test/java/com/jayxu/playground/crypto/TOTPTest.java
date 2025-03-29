/**
 * Authored by jayxu-@2025
 */
package com.jayxu.playground.crypto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.jayxu.playground.crypto.TOTP.Algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TOTPTest {
    @Test
    void test() {
        var alg = Algorithm.SHA256;

        var secret = TOTP.generateSecret(alg);
        var otp = TOTP.builder().algorithm(alg).secret(secret).build();
        var code = otp.generateCode();
        log.info("secret: {}, code: {}", secret, code);

        assertTrue(otp.verify(code));
        assertFalse(otp.verify(code, new Date(
            Instant.now().minusSeconds(otp.getPeriod()).toEpochMilli())));

        assertThrows(GeneralSecurityException.class,
            () -> TOTP.builder().secret(secret).build().verify(code));
    }

}
