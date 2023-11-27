/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.util;

import java.util.Random;

import lombok.experimental.UtilityClass;

/**
 * @author jayxu
 */
@UtilityClass
public class RandomUtils {
    public static <T extends Enum<T>> T randomEnum(Class<T> e) {
        var r = new Random();
        Enum<?>[] values = e.getEnumConstants();

        return (T) values[r.nextInt(values.length)];
    }
}
