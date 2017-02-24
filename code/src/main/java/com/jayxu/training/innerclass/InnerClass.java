/**
 * Copyright(c) 2011-2011 by Jay Xu All Rights Reserved
 */
package com.jayxu.training.innerclass;

/**
 * @author ijay
 */
public class InnerClass {
    public void repeat(Runnable runnable, int times) {
        for (int i = 0; i < times; i++) {
            runnable.run();
            runnable.toString();
        }
    }

    public static void main(String[] args) {
        InnerClass c = new InnerClass() {

            /*
             * (non-Javadoc)
             * @see
             * com.nazca.training.innerclass.InnerClass#repeat(java.lang.
             * Runnable, int)
             */
            @Override
            public void repeat(Runnable runnable, int times) {
                super.repeat(runnable, times);
            }

        };

        Runnable runnable = () -> System.out.println("hello world");
        new InnerClass().repeat(runnable, 10);
    }
}
