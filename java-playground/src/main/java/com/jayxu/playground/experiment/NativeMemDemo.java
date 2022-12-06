///**
// * Authored by jayxu @2021
// */
//package com.jayxu.playground.experiment;
//
//import jdk.incubator.foreign.MemorySegment;
//import jdk.incubator.foreign.ResourceScope;
//
//public class NativeMemDemo {
//    public static void main(String[] args) {
//        try (var res = ResourceScope.newConfinedScope();) {
//            var mem = MemorySegment
//                .allocateNative(1024, res);
//            System.out.println(mem.address().toString());
//        }
//    }
//}
