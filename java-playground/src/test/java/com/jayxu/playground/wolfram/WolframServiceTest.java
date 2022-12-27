/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.wolfram;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author xujiajing
 */
class WolframServiceTest {

    @Test
    void testQuery() {
        var service = WolframService.init();

        Arrays.asList("fibonacci(200)", "sqrt(112233)", "12^222", "who are you")
            .parallelStream().forEach(q -> {
                try {
                    System.out
                        .println(q + ": " + service.query(q).execute().body()
                            .extractPrimaryResult());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}
