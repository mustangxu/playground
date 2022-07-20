/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import java.math.BigInteger;

import lombok.experimental.UtilityClass;

/**
 * @author xujiajing
 */
@UtilityClass
public class NumberUtils {
    public static double fastLog10(BigInteger bNum) {
        var str = "." + bNum;
        return Math.log10(Double.parseDouble(str)) + str.length() - 1;
    }
}
