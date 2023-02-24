/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author xujiajing
 */
class FibonacciTest {

    /**
     * Test method for
     * {@link com.jayxu.playground.algorithm.Fibonacci#fibonacci(long)}.
     */
    @Test
    void testFibonacci() {
        for (var i = 1; i <= 200; i++) {
            System.out.println(i + ": " + Fibonacci.fibonacci(i));
            // System.out.println(Fibonacci.CACHE.size());
        }
    }

    @Test
    void testFibonacciFromWolfram() {
        final var N = 500;
        var n2 = Fibonacci.fibonacciFromWolfram(N);
        System.out.println(n2);

        assertEquals(Fibonacci.fibonacci(N), n2);
    }
}
