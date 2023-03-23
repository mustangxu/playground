/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.mailgun;

import java.util.List;

import lombok.Data;

/**
 * @author xujiajing
 */
@Data
public class MailgunRequest {
    String from;
    List<String> to;
    String subject;
    String text;
    String html;
}
