/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.spring;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.model.ApplicationModules;

/**
 * @author xujiajing
 */
class PlaygroundApplicationTest {

    @Test
    void test() {
        var modules = ApplicationModules.of(PlaygroundApplication.class);
        modules.forEach(System.out::println);

        // new Documenter(modules).writeModuleCanvases();
    }

}
