/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.experiment;

import java.util.concurrent.CountDownLatch;

/**
 * @author jayxu
 */
public class ScopedValueDemo {
    private static final ScopedValue<String> value = ScopedValue.newInstance();

    private static String demo(String name) throws Exception {
        return ScopedValue.where(ScopedValueDemo.value, name)
                .call(() -> "hello world, " + value.get() + " in [" + Thread.currentThread().getName() + "]");
    }

    void main() throws Exception {
        var n = 10;
        var latch = new CountDownLatch(n);

        for (var i = 0; i < n; i++) {
            new Thread(() -> {
                try {
                    var ret = demo("" + System.nanoTime());
                    System.out.println(ret);

                    latch.countDown();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        latch.await();
    }
}
