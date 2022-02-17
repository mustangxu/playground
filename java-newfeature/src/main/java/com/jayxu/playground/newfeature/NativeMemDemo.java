/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.newfeature;

import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;

public class NativeMemDemo {
    public static void main(String[] args) {
        var address = MemorySegment
            .allocateNative(4, ResourceScope.newConfinedScope()).address();
        System.out.println(address.toString());
    }
}
