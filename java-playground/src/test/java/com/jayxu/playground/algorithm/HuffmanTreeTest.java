/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author jayxu
 */
class HuffmanTreeTest {

    @Test
    void test() throws Exception {
        Map<Character, Integer> freq = new ConcurrentHashMap<>();

        try (var r = new BufferedReader(new InputStreamReader(
            this.getClass().getClassLoader()
                .getResourceAsStream("sample-text")));) {
            r.lines().parallel().map(String::chars).reduce(IntStream::concat)
                .get().parallel()
                .forEach(ch -> freq.merge((char) ch, 1, Math::addExact));
        }

        var tree = new HuffmanTree<>(freq);

        printTree(tree);

        var code = tree.encode('e');
        System.out.println(Arrays.toString(code));
        assertEquals('e', tree.decode(code));

        for (var ch = '0'; ch <= 'z'; ch++) {
            System.out.println(ch + ": " + Arrays.toString(tree.encode(ch)));
        }

        var node = tree.root.traverseMatch('e');
        System.out.println(node);
        System.out.println(node.replace('!'));
    }

    @Test
    void testRemove() {
        var tree = new HuffmanTree<>(
            Map.of('a', 1, 'e', 11, 'i', 3, 'o', 0, 'u', 7));

        printTree(tree);
        tree.remove('i');
        printTree(tree);
    }

    private static void printTree(HuffmanTree<?> tree) {
        tree.nodeStream().forEach(n -> System.out
            .println(
                n.getValue() + " [w: " + n.getWeight() + "], Lv."
                    + n.getLevel()));
    }
}
