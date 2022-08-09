/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author xujiajing
 */
@RequiredArgsConstructor
public class BinaryTree<T extends Comparable<T>> implements Collection<T> {
    @Getter
    private TreeNode root;
    private int size;
    @Getter
    @Setter
    @NonNull
    private Order order;
    /**
     * Starts from 0
     */
    @Getter
    private int depth;

    enum Order {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }

    public BinaryTree() {
        this(Order.PRE_ORDER);
    }

    @Data
    @RequiredArgsConstructor()
    public class TreeNode {
        private final T value;
        /**
         * Starts from 0
         */
        private int level;
        private TreeNode left;
        private TreeNode right;

        private TreeNode insertLeft(@NonNull T v) {
            if (this.left == null) {
                this.left = new TreeNode(v);
                this.left.level = this.level + 1;

                return this.left;
            }

            return this.left.insertChild(v);
        }

        private TreeNode insertRight(@NonNull T v) {
            if (this.right == null) {
                this.right = new TreeNode(v);
                this.right.level = this.level + 1;

                return this.right;
            }

            return this.right.insertChild(v);
        }

        public boolean isRoot() {
            return this.level == 0;
        }

        public TreeNode insertChild(@NonNull T v) {
            if (v.compareTo(this.value) <= 0) {
                return this.insertLeft(v);
            }

            return this.insertRight(v);
        }

        <V> void traverseOrdered(AtomicInteger counter,
                BiConsumer<AtomicInteger, V> fun,
                Function<TreeNode, V> mapper) {
            switch (BinaryTree.this.order) {
                case PRE_ORDER:
                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper);
                    }

                    fun.accept(counter, mapper.apply(this));
                    counter.incrementAndGet();

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper);
                    }

                    break;
                case IN_ORDER:
                    fun.accept(counter, mapper.apply(this));
                    counter.incrementAndGet();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper);
                    }

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper);
                    }

                    break;
                case POST_ORDER:
                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper);
                    }

                    fun.accept(counter, mapper.apply(this));
                    counter.incrementAndGet();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper);
                    }

                    break;
                case LEVEL_ORDER:
                    break;
            }
        }

        public void traverseValue(Consumer<T> fun) {
            this.traverse(fun, TreeNode::getValue);
        }

        public void traverseNode(Consumer<TreeNode> fun) {
            this.traverse(fun, n -> n);
        }

        <V> void traverse(Consumer<V> fun, Function<TreeNode, V> mapper) {
            this.traverseOrdered(new AtomicInteger(),
                (i, v) -> fun.accept(v), mapper);
        }

        boolean traverseMatch(T v) {
            if (this.value.equals(v)) {
                return true;
            }

            if (v.compareTo(BinaryTree.this.root.value) < 0) {
                return this.left != null && this.left.traverseMatch(v);
            }

            return this.right != null && this.right.traverseMatch(v);
        }
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "NULL root";
        }

        return String.format("order: %s, size: %s, depth: %s\nroot: %s",
            this.order, this.size, this.depth, this.root.toString());
    }

    public String toString(String separator) {
        if (this.root == null) {
            return "NULL root";
        }

        var sb = new StringBuilder();
        this.root.traverseValue(v -> sb.append(v).append(separator));

        return sb.delete(sb.length() - separator.length(), sb.length())
            .toString();
    }

    @Override
    public Iterator<T> iterator() {
        var list = new LinkedList<T>();

        if (this.root != null) {
            this.root.traverseValue(list::add);
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public T next() {
                return list.poll();
            }
        };
    }

    public Iterator<TreeNode> nodeIterator() {
        var list = new LinkedList<TreeNode>();

        if (this.root != null) {
            this.root.traverseNode(list::add);
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public TreeNode next() {
                return list.poll();
            }
        };
    }

    public Stream<TreeNode> nodeStream() {
        return StreamSupport.stream(Spliterators
            .spliterator(this.nodeIterator(), this.size, 0), false);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        if (this.root == null) {
            return false;
        }

        return this.root.traverseMatch((T) o);
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] a) {
        var array = a.length >= this.size ? a
            : (E[]) Array.newInstance(a.getClass().getComponentType(),
                this.size);

        if (this.root == null) {
            return array;
        }

        this.root.traverseOrdered(new AtomicInteger(),
            (j, t) -> array[j.intValue()] = (E) t, TreeNode::getValue);

        return array;
    }

    @Override
    public boolean add(T e) {
        this.size++;

        if (this.root == null) {
            this.root = new TreeNode(e);
        } else {
            var node = this.root.insertChild(e);
            this.depth = Math.max(node.level, this.depth);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.stream().forEach(this::add);

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }
}
