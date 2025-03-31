/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.util;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

/**
 * @author jayxu
 */
class YamlUtilsTest {

    @Test
    void test() throws Exception {
        try (var is = this.getClass().getClassLoader()
            .getResourceAsStream("apollo.yaml");) {
            var string = YamlUtils.parseApollo(is);

            var f = File.createTempFile("apollo-", ".yaml");
            System.out.println(f.getAbsolutePath());

            try (var w = new FileWriter(f)) {
                w.write(string);
            }
        }
    }

}
