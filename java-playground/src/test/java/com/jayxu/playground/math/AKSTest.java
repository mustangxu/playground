/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * @author xujiajing
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
        var max = 10_000L;
        var count = LongStream.rangeClosed(1, max).filter(AKS::isPrime).count();

        assertEquals(1229, count, "1229 primes in [1, 10000]");
    }

    @Benchmark
    public void benchmark() {
        this.test();
    }
}
