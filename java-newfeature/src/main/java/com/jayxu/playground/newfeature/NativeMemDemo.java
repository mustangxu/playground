/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.newfeature;

import jdk.incubator.foreign.MemorySegment;

public class NativeMemDemo {
    public static void main(String[] args) {
        var address = MemorySegment.allocateNative(4).baseAddress();
        System.out.println(address.toString());
    }
}
