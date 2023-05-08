/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jayxu.playground.wolfram.WolframService;

import lombok.experimental.UtilityClass;

/**
 * @author xujiajing
 */
@UtilityClass
public class Fibonacci {
    private static final BigDecimal SQRT_V = BigDecimal.valueOf(Math.sqrt(5));
    private static final BigDecimal HALF = new BigDecimal("0.5");
    private static final BigDecimal PHI = SQRT_V.add(BigDecimal.ONE)
        .multiply(HALF);
    static final Map<Integer, BigInteger> CACHE = new ConcurrentHashMap<>();

    static {
        CACHE.put(1, BigInteger.ONE);
        CACHE.put(2, BigInteger.ONE);
    }

    public static BigInteger fibonacci(int n) {
        // precision loss above 70
        if (n <= 70) {
            // Math.floor(Math.pow(phi, n) / SQRT_V + 0.5)
            return CACHE.computeIfAbsent(n, i -> PHI.pow(i)
                .divide(SQRT_V, RoundingMode.HALF_UP).add(HALF).toBigInteger());
        }

        var result = CACHE.get(n);
        if (result == null) {
            result = fibonacci(n - 1).add(fibonacci(n - 2));
            CACHE.putIfAbsent(n, result);
        }

        return result;
    }

    public static BigInteger fibonacciFromWolfram(int n) {
        return WolframService.init().query("fibonacci(" + n + ")")
            .extractPrimaryResult(BigInteger::new);
    }
}
