/*
 * Jay Xu
 * All Rights Reserved
 */
package com.jayxu.training.lang;

/**
 *
 * @author ijay
 */
public class SuperOverrideOverload {

    public void overload(int number) {
        System.out.println("super int overload");
    }

    public void overload(Integer number) {
        System.out.println("super Integer overload");
    }

    public void overload(long number) {
        System.out.println("super long overload");
    }

    public void overload(Long number) {
        System.out.println("super Long overload");
    }

    public void overload(Object number) {
        System.out.println("super Object overload");
    }

    public static void main(String[] args) {
        new SuperOverrideOverload().overload(100);
    }
}
