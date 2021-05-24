/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.util;

import java.util.Arrays;

public class Collections {
    public static int[] bitmapSort(int[] set) {
        var max = Arrays.stream(set).max().getAsInt();
        var array = new int[max + 1];
        var ret = new int[set.length];

        for (int i : set) {
            array[i] = 1;
        }

        for (int i = 0, j = 0; i < array.length; i++) {
            if (array[i] == 1) {
                ret[j++] = i;
            }
        }

        return ret;
    }
}
