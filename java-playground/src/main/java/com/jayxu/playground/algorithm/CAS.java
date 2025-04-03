/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author jayxu
 */
@Data
public class CAS {
    //    private static final Unsafe UNSAFE;
    //    private static final long OFFSET;
    private int value;
    private VarHandle vh;

    //    static {
    //        try {
    //            var field = Unsafe.class.getDeclaredField("theUnsafe");
    //            field.setAccessible(true);
    //            UNSAFE = (Unsafe) field.get(null);
    //
    //            OFFSET = UNSAFE
    //                .objectFieldOffset(CAS.class.getDeclaredField("value"));
    //        } catch (Exception e) {
    //            throw new RuntimeException(e);
    //        }
    //    }

    @SneakyThrows
    public CAS() {
        this.vh = MethodHandles.lookup().findVarHandle(this.getClass(), "value", int.class);
    }

    public boolean updateValue(int newValue) {
        return this.vh.compareAndSet(this, this.value, newValue);
        //        return UNSAFE.compareAndSwapInt(this, OFFSET, this.value, newValue);
    }
}
