/**
 * Authored by jayxu-@2025
 */
package com.jayxu.playground.crypto;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.lang3.ArrayUtils;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.XSlf4j;

@XSlf4j
@Builder
@Data
public final class TOTP {
    private static SecureRandom sr = new SecureRandom();
    private static Base32 base32 = new Base32();
    @Builder.Default
    private Algorithm algorithm = Algorithm.SHA1;
    @Builder.Default
    private int codeLength = 6;
    @Builder.Default
    private int period = 30;
    private String secret;

    public enum Algorithm {
        @Deprecated
        SHA1(160, "HmacSHA1"),
        SHA256(256, "HmacSHA256"),
        SHA512(512, "HmacSHA512");

        private final int bits;
        private final String hmac;

        Algorithm(int bits, String hmac) {
            this.bits = bits;
            this.hmac = hmac;
        }

        public int bits() {
            return this.bits;
        }

        public String hmac() {
            return this.hmac;
        }

    }

    public static String generateSecret(Algorithm algorithm) {
        var random = new byte[algorithm.bits() / 8];
        TOTP.sr.nextBytes(random);

        return TOTP.log.exit(TOTP.base32.encodeToString(random));
    }

    @SneakyThrows
    public String generateCode(Date time) {
        Long counter = time.getTime() / 1000 / this.period;
        TOTP.log.entry(this.secret, counter);

        var hash = this.generateHash(TOTP.base32.decode(this.secret), Longs.toByteArray(counter));
        return TOTP.log.exit(this.getCodeFromHash(hash));
    }

    @SneakyThrows
    public String generateCode() {
        return this.generateCode(new Date());
    }

    @SneakyThrows
    private byte[] generateHash(byte[] secret, byte[] counter) {
        if (secret.length != this.algorithm.bits() / 8) {
            throw new GeneralSecurityException("Key length not match");
        }

        var mac = Mac.getInstance(this.algorithm.hmac());
        mac.init(new SecretKeySpec(secret, this.algorithm.hmac()));

        return mac.doFinal(counter);
    }

    public boolean verify(String code, Date time) {
        if (this.codeLength != code.length()) {
            return false;
        }

        return this.generateCode(time).equalsIgnoreCase(code);
    }

    public boolean verify(String code) {
        return this.verify(code, new Date());
    }

    private String getCodeFromHash(byte[] hash) {
        /*
        Get last 4 bits of hash as offset:
        Use the bitwise AND (&) operator to select last 4 bits
        Mask should be 00001111 = 15 = 0xF
        Last byte of hash & 0xF = last 4 bits:
        Example:
        Input: decimal 219 as binary: 11011011 &
        Mask: decimal 15 as binary:   00001111
        -----------------------------------------
        Output: decimal 11 as binary: 00001011
         */
        var offset = hash[hash.length - 1] & 0xF;

        // Get 4 bytes from hash from offset to offset + 3
        var truncatedHashInBytes = ArrayUtils.subarray(hash, offset, offset + 4);

        var truncatedHash = Ints.fromByteArray(truncatedHashInBytes);

        // Mask most significant bit
        truncatedHash &= 0x7FFFFFFF;

        // Modulo (%) truncatedHash by 10^codeLength
        truncatedHash %= Math.pow(10, this.codeLength);

        // Left pad with 0s for an n-digit code
        return String.format("%0" + this.codeLength + "d", truncatedHash);
    }
}
