/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.jayxu.playground.algorithm.BinaryTree.Order;

/**
 * @author xujiajing
 */
class BinaryTreeTest {

    @Test
    void test() {
        var tree = new BinaryTree<Integer>();
        var inTree = new BinaryTree<Integer>(Order.IN_ORDER);
        var max = 50;
        var r = new Random();

        for (var i = 0; i < max; i++) {
            var num = r.nextInt(max) * r.nextInt(max);
            tree.add(num);
            inTree.add(num);
        }
        tree.add(-1);
        inTree.add(-1);

        System.out.println(tree);
        System.out.println("PRE_ORDER: " + tree.toString(", "));

        System.out.println(tree.stream().map(Objects::toString)
            .collect(Collectors.joining(", ")));

        assertEquals(max + 1, tree.size(), "size()");
        assertTrue(tree.contains(-1), "contains()");
        assertFalse(tree.containsAll(List.of(-1, -2)), "containsAll()");

        var postTree = new BinaryTree<Integer>(Order.POST_ORDER);
        postTree.addAll(tree);
        System.out.println("POST_ORDER: " + postTree.toString(", "));

        System.out.println("IN_ORDER: " + inTree.toString(", "));

        var array = new Integer[tree.size()];
        var ret = tree.toArray(array);
        assertSame(array, ret);
        System.out.println(Arrays.toString(ret));

        array = new Integer[tree.size() - 1];
        ret = tree.toArray(array);
        assertNotSame(array, ret);
        System.out.println(Arrays.toString(ret));
    }

}
