/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author xujiajing
 */
@RequiredArgsConstructor
public class BinaryTree<T extends Comparable<T>> implements Collection<T> {
    private TreeNode root;
    private int size;
    private final Order order;
    /**
     * Starts from 0
     */
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

        void traverseOrdered(AtomicInteger counter,
                BiConsumer<AtomicInteger, T> fun) {
            switch (BinaryTree.this.order) {
                case PRE_ORDER:
                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun);
                    }

                    fun.accept(counter, this.value);
                    counter.incrementAndGet();

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun);
                    }

                    break;
                case IN_ORDER:
                    fun.accept(counter, this.value);
                    counter.incrementAndGet();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun);
                    }

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun);
                    }

                    break;
                case POST_ORDER:
                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun);
                    }

                    fun.accept(counter, this.value);
                    counter.incrementAndGet();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun);
                    }

                    break;
                case LEVEL_ORDER:
                    break;
            }
        }

        void traverse(Consumer<T> fun) {
            this.traverseOrdered(new AtomicInteger(), (i, v) -> fun.accept(v));
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
        this.root.traverse(v -> sb.append(v).append(separator));

        return sb.delete(sb.length() - separator.length(), sb.length())
            .toString();
    }

    @Override
    public Iterator<T> iterator() {
        var list = new LinkedList<T>();

        if (this.root != null) {
            this.root.traverse(list::add);
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
            (j, t) -> array[j.intValue()] = (E) t);

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
