/**
 *
 */
package com.jayxu.playground.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author Jay Xu @2023
 */
public class LambdaHandler
        implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> map, Context context) {
        var log = context.getLogger();
        var name = map.getOrDefault("name", "DEFAULT");

        log.log("name: " + name + "\n");

        return "Hello, " + name;
    }

}
