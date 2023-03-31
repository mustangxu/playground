/**
 *
 */
package com.jayxu.playground.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

/**
 * @author Jay Xu @2023
 */
public class LambdaHandler implements RequestHandler<SNSEvent, String> {

    @Override
    public String handleRequest(SNSEvent event, Context context) {
        var log = context.getLogger();
        var records = event.getRecords();

        for (var r : records) {
            log.log("EventSource: " + r.getEventSource() + "\n");

            var sns = r.getSNS();
            log.log("Subject: " + sns.getSubject() + "\n");
            log.log("Message: " + sns.getMessage() + "\n");
            log.log("Type: " + sns.getType() + "\n");

            sns.getMessageAttributes().forEach((k, v) -> {
                log.log(k + ": " + v + "\n");
            });
        }

        return event.toString();
    }

}
