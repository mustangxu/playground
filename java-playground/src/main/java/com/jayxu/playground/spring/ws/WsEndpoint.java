/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * @author jayxu
 */
@RestController
public class WsEndpoint {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.name()) + "!");
    }

}
