/**
 * Copyright(c) 2016-2018 by Euler
 * All Rights Reserved
 */
package com.jayxu.playground.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jay
 */
@Slf4j
@SuppressWarnings("unused")
class ClassIntrospectorTest {
    private static class ObjectA {
        private byte b1; // 1
        private byte b2; // 1
        private byte b3;  // 1
    }

    private static class ObjectC {
        private ObjectD[] array = new ObjectD[2];
        private String nullString;

        public ObjectC() {
            this.array[0] = new ObjectD();
        }
    }

    private static class ObjectD {
        private byte[] value = new byte[3];
    }

    @Test
    void test() throws Exception {
        var i = ClassIntrospector.introspect(null);
        ClassIntrospectorTest.doLog(i);

        i = ClassIntrospector.introspect("helloworld");
        ClassIntrospectorTest.doLog(i);
        Assertions.assertEquals(24 + 32, i.getDeepSize());

        i = ClassIntrospector.introspect(new ObjectA());
        ClassIntrospectorTest.doLog(i);
        Assertions.assertEquals(16, i.getDeepSize());

        i = ClassIntrospector.introspect(new ObjectC());
        ClassIntrospectorTest.doLog(i);
        Assertions.assertEquals(16 + 24 + 24 + 16, i.getDeepSize());
    }

    @Test
    void testComplex() throws Exception {
        Integer[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
        int[] a2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

        List<Integer> l = Arrays.asList(a);
        ClassIntrospectorTest
            .doLog(ClassIntrospector.introspect(new LinkedList<>(l)));
        ClassIntrospectorTest
            .doLog(ClassIntrospector.introspect(new ArrayList<>(l)));
        ClassIntrospectorTest
            .doLog(ClassIntrospector.introspect(IntLists.mutable.with(a2)));

        ClassIntrospectorTest
            .doLog(ClassIntrospector.introspect(new HashSet<>(l)));
        ClassIntrospectorTest
            .doLog(ClassIntrospector.introspect(Sets.mutable.with(a)));
    }

    private static void doLog(ObjectInfo info) {
        ClassIntrospectorTest.log.info(info.toString());
        ClassIntrospectorTest.log.trace(info.toDetails());
    }
}
