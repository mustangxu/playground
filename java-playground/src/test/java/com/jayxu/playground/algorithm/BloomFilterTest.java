/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.jayxu.playground.lang.ClassIntrospector;
import com.jayxu.playground.math.AKS;

/**
 * @author xujiajing
 */
class BloomFilterTest {
    @Test
    void test() {
        final var cap = 10_000_000L;
        BloomFilter<Long> filter = BloomFilter.create(Funnels.longFunnel(),
            cap / 10);
        LongStream.rangeClosed(1, cap).parallel().filter(AKS::isPrime)
            .forEach(filter::put);

        System.out.println(filter.approximateElementCount());
        System.out.println(filter.expectedFpp());
        System.out.println(ClassIntrospector.introspect(filter));

        assertTrue(filter.mightContain(2L));
        assertFalse(filter.mightContain(4L));
    }
}
