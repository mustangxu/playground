/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.reflection;

public class MathTest {
    private final Math math = new Math();

    @Test
    public void testAdd() {
        var result = this.math.add(1, 2);
        System.out.println(result != 3);
    }
}
