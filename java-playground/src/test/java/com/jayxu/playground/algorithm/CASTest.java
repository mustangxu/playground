package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.eclipse.collections.impl.Counter;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(2)
@State(Scope.Benchmark)
public class CASTest {
    private ExecutorService executor;
    private int counter;

    public static void main(String[] args) throws Exception {
        var opt = new OptionsBuilder().include(CASTest.class.getSimpleName())
            .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
    }

    @TearDown
    public void teardown() {
        this.executor.shutdown();
    }

    @Test
    void test() {
        var cas = new CAS();
        assertTrue(cas.updateValue(1));
        assertEquals(1, cas.getValue());
        assertTrue(cas.updateValue(1));

        assertTrue(cas.updateValue(2));
        assertEquals(2, cas.getValue());
    }

    @Benchmark
    public void benchmarkCAS() throws Exception {
        var cas = new CAS();

        this.doBenchmark(() -> cas.updateValue(this.counter++));
    }

    @Benchmark
    public void benchmarkSynchronized() throws Exception {
        var c = new Counter();

        this.doBenchmark(() -> {
            synchronized (c) {
                c.increment();
            }

            return c.getCount();
        });
    }

    @Benchmark
    public void benchmarkLock() throws Exception {
        var l = new ReentrantLock();

        this.doBenchmark(() -> {
            var i = 0;
            l.lock();
            i++;
            l.unlock();

            return i;
        });
    }

    @Benchmark
    public void benchmarkAtomic() throws Exception {
        var v = new AtomicInteger();

        this.doBenchmark(() -> v.incrementAndGet());
    }

    private void doBenchmark(Supplier<?> sup) throws Exception {
        var f1 = this.executor.submit(() -> sup.get());
        var f2 = this.executor.submit(() -> sup.get());

        f1.get();
        f2.get();
    }

}
