/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.reflection;

/**
 * @author ijay
 * 
 */
public class MathTest {
    private Math math = new Math();

    @Test
    public void testAdd() {
        int result = this.math.add(1, 2);
        System.out.println(3 != result);
    }
}
