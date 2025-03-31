/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.io.Serial;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jayxu
 * @param <T>
 */
public class HuffmanTree<T> extends Tree<T> {
    @Serial
    private static final long serialVersionUID = -2503306000582479972L;
    private LinkedList<HuffmanTreeNode<T>> dest;

    @NoArgsConstructor
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class HuffmanTreeNode<T> extends TreeNode<T>
            implements Comparable<HuffmanTreeNode<T>> {
        @Serial
        private static final long serialVersionUID = 4547144338353998776L;
        private int weight;

        public HuffmanTreeNode(T value) {
            super(value);
        }

        @Override
        public int compareTo(HuffmanTreeNode<T> o) {
            return Objects.compare(this, o,
                Comparator.comparingInt(HuffmanTreeNode::getWeight));
        }

        @Override
        protected HuffmanTreeNode<T> create(T v) {
            return new HuffmanTreeNode<>(v);
        }

        @Override
        protected boolean chooseLeft(T v) {
            return true;
        }
    }

    public HuffmanTree(Map<T, Integer> weightedMap) {
        super(Order.LEVEL_ORDER);

        this.dest = Lists.newLinkedList();
        weightedMap.entrySet().stream().map(this::buildNode)
            .sorted(Comparator.comparing(HuffmanTreeNode<T>::getWeight))
            .forEach(this.dest::add);

        this.buildTree();
    }

    private void buildTree() {
        var copy = Lists.newLinkedList(this.dest);
        this.size = copy.size();

        while (copy.size() > 1) {
            var n1 = copy.removeFirst();
            var n2 = copy.removeFirst();

            var p = this.buildParent(n1, n2);
            copy.add(p);
            Collections.sort(copy);
        }

        this.root = copy.pollFirst();
        this.recalculateLevelsNDepth();
    }

    private HuffmanTreeNode<T> buildNode(Entry<T, Integer> entry) {
        var node = new HuffmanTreeNode<T>();
        node.value = entry.getKey();
        node.weight = entry.getValue();

        return node;
    }

    private HuffmanTreeNode<T> buildParent(HuffmanTreeNode<T> n1,
            HuffmanTreeNode<T> n2) {
        var node = new HuffmanTreeNode<T>();
        node.weight = n1.weight + n2.weight;
        n1.parent = node;
        n2.parent = node;

        if (n1.weight <= n2.weight) {
            node.left = n1;
            node.right = n2;
        } else {
            node.left = n2;
            node.right = n1;
        }

        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return TreeIterator.valueIterator(this.root, Order.LEVEL_ORDER);
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (this.dest.removeIf(n -> n.value != null && n.value.equals(o))) {
            this.buildTree();

            return true;
        }

        return false;
    }

    public byte[] encode(T v) {
        if (this.root == null) {
            return new byte[0];
        }

        var node = this.root.traverseMatch(v);
        if (node == null) {
            return new byte[0];
        }

        var code = new byte[node.level];
        var p = node.parent;

        if (node.isRoot()) {
            code[0] = 0;
        } else {
            do {
                code[node.level - 1] = (byte) (node == p.left ? 0 : 1);
                node = p;
                p = node.parent;
            } while (p != null);
        }

        return code;
    }

    public T decode(byte[] code) {
        var node = this.root;

        for (byte b : code) {
            node = b == 0 ? node.left : node.right;
        }

        return node.value;
    }

    @Override
    public void clear() {
        super.clear();
        this.dest.clear();
    }
}
