/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author jayxu
 */
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
// @Threads(2)
public class AKSTest {
    public static void main(String[] args) throws Exception {
        var opt = new OptionsBuilder()
            .include(AKSTest.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    @Test
    void test() {
        var max = 1_000L;
        var count = LongStream.rangeClosed(1, max)
            .filter(n -> AKS.isPrime(n, true)).count();

        assertEquals(168, count, "168 primes in [1, 1000]");
    }

    @Test
    void testLargestFactor() {
        assertEquals(7, AKS.AKS_LONG.largestFactor(28));
        assertEquals(7, AKS.AKS_LONG.largestFactor(7));
        assertEquals(1, AKS.AKS_LONG.largestFactor(1));
    }

    @Test
    void testIsPrime() {
        assertTrue(AKS.isPrime(11));
        assertFalse(AKS.isPrime(12));
    }

    @Benchmark
    public void benchmark() {
        this.test();
    }
}
