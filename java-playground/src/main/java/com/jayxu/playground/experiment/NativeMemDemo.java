/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.experiment;

import java.lang.foreign.Arena;

/**
 * @author jayxu
 */
public class NativeMemDemo {
    void main() {
        try (var arena = Arena.ofConfined();) {
            var mem = arena.allocate(1024L);
            System.out.println(mem.address());
        }
    }
}
