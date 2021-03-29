package com.jayxu.playground.util;

import java.util.Random;

import org.junit.jupiter.api.Test;

class CollectionsTest {

    @Test
    void test() {
        var limit = 1000;
        var array = new Random().ints(0, 2 * limit).distinct().limit(limit)
            .toArray();

//        for (var i : array) {
//            System.out.println(i);
//        }

        var ret = Collections.bitmapSort(array);
        System.out.println("DONE, size: " + ret.length);

        for (var i : ret) {
            System.out.println(i);
        }
    }
}
