package com.jayxu.playground;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.jayxu.playground.lang.ClassIntrospector;

// @RunWith(JQF.class)
class TrieTest {
    @Test
    void test() throws Exception {
        var map = new HashedMap<String, String>();
        var key = "ja";
        var faker = new Faker();

        for (var i = 0; i < 100; i++) {
            map.put(faker.name().username(), faker.name().fullName());
        }
        System.out.println(map.size());

        Assertions.assertFalse(map.containsKey(key));

        var trie = new PatriciaTrie<>(map);
        var insp = ClassIntrospector.introspect(trie);
        System.out.println(insp);
        Assertions.assertFalse(trie.containsKey(key));

        var res = trie.prefixMap(key);
        res.entrySet().stream().forEach(System.out::println);
    }
}
