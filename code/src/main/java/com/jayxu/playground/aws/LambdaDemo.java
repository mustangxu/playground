/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaDemo implements RequestHandler<Object, String> {
    @Override
    public String handleRequest(Object input, Context context) {
        var log = context.getLogger();

        log.log("[INPUT] " + input.toString() + "\n");
        log.log("[CONTEXT] " + context.toString() + "\n");

        return "hello world";
    }

}
