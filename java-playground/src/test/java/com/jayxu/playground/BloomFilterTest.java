/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.jayxu.playground.math.AKS;

/**
 * @author xujiajing
 */
class BloomFilterTest {
    @Test
    void test() {
        final var cap = 1_000_000L;
        BloomFilter<Long> filter = BloomFilter.create(Funnels.longFunnel(),
            cap);
        LongStream.rangeClosed(1, cap).parallel().filter(AKS::isPrime)
            .forEach(filter::put);

        System.out.println(filter.approximateElementCount());
    }
}
