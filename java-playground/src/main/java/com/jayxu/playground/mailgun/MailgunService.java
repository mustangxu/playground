/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.mailgun;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xujiajing
 */
public interface MailgunService {
    @PostMapping(path = "/messages",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, ?> send(@ModelAttribute MailgunRequest request);
}
