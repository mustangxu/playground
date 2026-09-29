/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.math;

import java.math.BigInteger;

import lombok.experimental.UtilityClass;

/**
 * @author jayxu
 */
@UtilityClass
public class NumberUtils {
    public double fastLog10(BigInteger bNum) {
        var str = "." + bNum;
        return StrictMath.log10(Double.parseDouble(str)) + str.length() - 1;
    }
}
