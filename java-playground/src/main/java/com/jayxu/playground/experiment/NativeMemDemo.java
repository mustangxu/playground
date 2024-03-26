/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.experiment;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemoryLayout.PathElement;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
public class NativeMemDemo {
    private static Linker linker = Linker.nativeLinker();

    void main() throws Throwable {
        try (var arena = Arena.ofConfined();) {
            // allocate 1KB
            var mem = arena.allocate(1024L);
            logSegment(mem);

            /*
             * struct Point {
             *      int x;
             *      int y;
             *      double price;
             * } pts[10];
             */
            var n = 10;
            var layout = MemoryLayout.sequenceLayout(n,
                MemoryLayout.structLayout(ValueLayout.JAVA_INT.withName("x"),
                    ValueLayout.JAVA_INT.withName("y"),
                    ValueLayout.JAVA_DOUBLE.withName("price")));
            mem = arena.allocate(layout);
            logSegment(mem);

            var xHandle = layout.varHandle(PathElement.sequenceElement(),
                PathElement.groupElement("x"));

            var yHandle = layout.varHandle(PathElement.sequenceElement(),
                PathElement.groupElement("y"));

            var priceHandle = layout.varHandle(PathElement.sequenceElement(),
                PathElement.groupElement("price"));

            for (var i = 0; i < n; i++) {
                xHandle.set(mem, (long) i, -i);
                yHandle.set(mem, (long) i, i * 2);
                priceHandle.set(mem, (long) i, Math.sqrt(i));
            }

            var value = mem.toArray(ValueLayout.JAVA_INT);
            System.out.println(ArrayUtils.toString(value));

            // size_t strlen(char*);
            invoke("strlen", arena.allocateUtf8String("hello world"));
            // printf(char*)
            invoke("printf", arena.allocateUtf8String("hello world"));
        }
    }

    private static void logSegment(MemorySegment mem) {
        log.info("address: 0x{}, size: {}", Long.toHexString(mem.address()),
            mem.byteSize());
    }

    /**
     * Template for native function long xxx(char*)
     *
     * @return return value of invoke
     * @throws Throwable
     */
    private static long invoke(String funName, MemorySegment mem)
            throws Throwable {
        var ret = (long) linker.downcallHandle(
            linker.defaultLookup().find(funName).orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS))
            .invoke(mem);

        log.info("{}() = {}", funName, ret);

        return ret;
    }
}
