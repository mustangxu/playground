/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.mailgun.MailgunRequest;
import com.jayxu.playground.mailgun.MailgunService;

/**
 * @author jayxu
 */
@RestController
@RequestMapping("/mailgun")
public class MailgunController {
    @Autowired
    private MailgunService service;

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, ?> send(@ModelAttribute MailgunRequest request) {
        return this.service.send(request);
    }
}
