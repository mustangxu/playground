/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author xujiajing
 */
@RestController
public class DefaultController {
    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    @SecurityRequirement(name = "security")
    public String helloWorld() {
        return "world";
    }

    @GetMapping("/enc")
    public String encrypt(@RequestParam String raw) {
        return this.encryptor.encrypt(raw);
    }

    @GetMapping("/env")
    public String getEnv(@RequestParam String key) {
        return this.env.getProperty(key);
    }

}
