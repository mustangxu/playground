/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.lang;

public class SuperOverrideOverload {

    public void overload(@SuppressWarnings("unused") int number) {
        System.out.println("super int overload");
    }

    public void overload(@SuppressWarnings("unused") Integer number) {
        System.out.println("super Integer overload");
    }

    public void overload(@SuppressWarnings("unused") long number) {
        System.out.println("super long overload");
    }

    public void overload(@SuppressWarnings("unused") Long number) {
        System.out.println("super Long overload");
    }

    public void overload(@SuppressWarnings("unused") Object number) {
        System.out.println("super Object overload");
    }

    public static void main(String[] args) {
        new SuperOverrideOverload().overload(100);
    }
}
