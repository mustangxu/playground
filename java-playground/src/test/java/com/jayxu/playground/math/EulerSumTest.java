/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

/**
 * 1/1^2 + 1/2^2 + 1/3^2 + ...
 */
public class EulerSumTest {
    public static void main(String[] args) {
        final var MC = MathContext.DECIMAL128;
//        var sum = BigDecimal.ZERO.round(MC);

//        for (var i = 1; i <= 100_000; i++) {
//            sum = sum.add(BigDecimal.ONE.divide(new BigDecimal(i).pow(2), MC));
//
//            var res = sum.multiply(new BigDecimal(6)).sqrt(MC);
//            System.out.println("i = " + i + ", √(sum * 6) = " + res
//                + ", delta = " + res.subtract(new BigDecimal(Math.PI)));
//        }

        var res = IntStream.range(1, 1_000_000).parallel()
            .mapToObj(
                i -> new BigDecimal(6).divide(new BigDecimal(i).pow(2), MC))
            .reduce((BinaryOperator<BigDecimal>) BigDecimal::add).get()
            .sqrt(MC);

        System.out.println("√(sum * 6) = " + res + ", delta = "
            + res.subtract(new BigDecimal(Math.PI)).toPlainString());
    }
}
