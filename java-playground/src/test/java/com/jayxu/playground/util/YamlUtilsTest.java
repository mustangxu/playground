/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.util;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

/**
 * @author xujiajing
 */
class YamlUtilsTest {

    @Test
    void test() throws Exception {
        var string = YamlUtils.parseApollo(this.getClass().getClassLoader()
            .getResourceAsStream("apollo.yaml"));

        var f = File.createTempFile("apollo-", ".yaml");
        System.out.println(f.getAbsolutePath());

        try (var w = new FileWriter(f)) {
            w.write(string);
        }
    }

}
