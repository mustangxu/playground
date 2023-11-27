/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import lombok.Data;
import sun.misc.Unsafe;

/**
 * @author jayxu
 */
@Data
public class CAS {
    private static final Unsafe UNSAFE;
    private static final long OFFSET;
    private int value;

    static {
        try {
            var field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);

            OFFSET = UNSAFE
                .objectFieldOffset(CAS.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateValue(int newValue) {
        return UNSAFE.compareAndSwapInt(this, OFFSET, this.value, newValue);
    }
}
