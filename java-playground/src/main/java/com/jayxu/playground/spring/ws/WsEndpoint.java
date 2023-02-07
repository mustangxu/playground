/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

/**
 * @author xujiajing
 */
@Controller
public class WsEndpoint {
    @RequestMapping("/ws")
    public String ping() {
        return "pong";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        return new Greeting(
            "Hello, " + HtmlUtils.htmlEscape(message.name()) + "!");
    }

}
