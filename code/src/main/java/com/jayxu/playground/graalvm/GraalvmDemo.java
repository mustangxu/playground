package com.jayxu.playground.graalvm;

import org.graalvm.polyglot.Context;

public class GraalvmDemo {
    public static void main(String[] args) {
        try (var polyglot = Context.newBuilder()
            .allowExperimentalOptions(true)
            .build();) {
            // the use of shared array buffer requires the 'js.shared-array-buffer' option to be 'true'
            polyglot.eval("js", "console.log(new SharedArrayBuffer(1024))");
        }
    }
}
