/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

/**
 * @author xujiajing
 */
class AKSTest {
    @Test
    void test() {
        var max = 10000L;
        var primes = LongStream.rangeClosed(1, max)
            .mapToObj(n -> new Object[] { n, AKS.isPrime(n) })
            .filter(o -> (boolean) o[1]).map(o -> o[0]).toList();

        assertEquals(1229, primes.size(), "1229 primes in [1, 10000]");
    }
}
