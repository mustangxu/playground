/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.google.common.math.BigIntegerMath;

/**
 * @author jayxu
 */
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class NumberUtilsTest {
    private static final BigInteger NUM = new BigInteger(
        "594894916161614941651614949494161651614977");

    public static void main(String[] args) throws Exception {
        var opt = new OptionsBuilder()
            .include(NumberUtilsTest.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    @Test
    void testError() {
        var fast = NumberUtils.fastLog10(NUM);
        System.out.println(fast);
        var guava = BigIntegerMath.log10(NUM, RoundingMode.DOWN);
        System.out.println(guava);

        assertEquals((int) fast, guava);
    }

    @Benchmark
    public void fastLog10(Blackhole hole) {
        hole.consume(NumberUtils.fastLog10(NUM));
    }

    @Benchmark
    public void guava(Blackhole hole) {
        hole.consume(BigIntegerMath.log10(NUM, RoundingMode.DOWN));
    }
}
