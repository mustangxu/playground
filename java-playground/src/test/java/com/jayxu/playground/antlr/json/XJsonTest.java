/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.antlr.json;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
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

import lombok.extern.slf4j.XSlf4j;

/**
 * @author jayxu
 */
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
// @Threads(2)
@XSlf4j
public class XJsonTest {
    public static void main(String[] args) throws Exception {
        var opt = new OptionsBuilder()
            .include(XJsonTest.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    @Benchmark
    public void benchmark(Blackhole hole) throws Exception {
        hole.consume(parse("test.json"));
    }

    private static Object parse(String filename) throws Exception {
        try (var is = XJsonTest.class.getClassLoader()
            .getResourceAsStream(filename);) {
            return XJson.parse(is);
        }
    }

    @Test
    void test() throws Exception {
        log.entry();
        var o = parse("large-file.json");
        assertNotNull(o, "json");

        if (o instanceof Object[] array) {
            Arrays.stream(array).limit(20).forEach(System.out::println);
        } else {
            System.out.println(o);
        }

        log.exit();
    }
}
