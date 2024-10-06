/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.math;

/**
 * Calculate √2 ^ (√2 ^ (√2 ^ ...))
 */
public class PowSqrt2Test {
    private static final double SQRT_2 = Math.sqrt(2);

    public static void main(String[] args) {
        var tmp = SQRT_2;

        for (var i = 0; i < 100; i++) {
            tmp = Math.pow(SQRT_2, tmp);
            System.out.println("i = " + i + ", result = " + tmp);
        }

    }
}
