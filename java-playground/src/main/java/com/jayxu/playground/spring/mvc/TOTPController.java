/**
 * Authored by jayxu-@2025
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.crypto.TOTP;
import com.jayxu.playground.crypto.TOTP.Algorithm;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/otp")
public class TOTPController {
    @GetMapping("/secret")
    public Mono<String> generateSecret(@RequestHeader(defaultValue = "SHA1",
            required = false) Algorithm algorithm) {
        return Mono.just(TOTP.generateSecret(algorithm));
    }

    @GetMapping("/code")
    public Mono<String> generateCode(
            @RequestHeader(defaultValue = "SHA1",
                    required = false) Algorithm algorithm,
            @RequestHeader String secret, @RequestHeader(defaultValue = "6",
                    required = false) int codeLength) {
        return Mono.just(TOTP.builder().algorithm(algorithm).secret(secret)
            .codeLength(codeLength).build().generateCode());
    }

    @GetMapping("/verify")
    public Mono<Boolean> verify(
            @RequestHeader(defaultValue = "SHA1",
                    required = false) Algorithm algorithm,
            @RequestHeader String secret,
            @RequestHeader(defaultValue = "6", required = false) int codeLength,
            String code) {
        return Mono.just(TOTP.builder().algorithm(algorithm).secret(secret)
            .codeLength(codeLength).build().verify(code));
    }
}
