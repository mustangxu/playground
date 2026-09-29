/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.mailgun;

import java.util.List;

import lombok.Data;

/**
 * @author jayxu
 */
@Data
public class MailgunRequest {
    private String from;
    private List<String> to;
    private String subject;
    private String text;
    private String html;
}
