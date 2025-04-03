/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.collections.impl.Counter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * @param <T>
 *
 * @author jayxu
 */
@Data
@ToString(exclude = "parent")
@NoArgsConstructor
public abstract class TreeNode<T> implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 2971112099864422333L;
    protected T value;
    /**
     * Starts from 0
     */
    protected int level;
    protected TreeNode<T> left;
    protected TreeNode<T> right;
    protected TreeNode<T> parent;

    public TreeNode(T value) {
        this.value = value;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    protected <V> void traverseOrdered(Counter counter, BiConsumer<Counter, V> fun, Function<TreeNode<T>, V> mapper,
                                       Order order) {
        var v = mapper.apply(this);

        switch (order) {
            case PRE_ORDER:
                if (this.left != null) {
                    this.left.traverseOrdered(counter, fun, mapper, order);
                }

                fun.accept(counter, v);
                counter.increment();

                if (this.right != null) {
                    this.right.traverseOrdered(counter, fun, mapper, order);
                }

                break;
            case IN_ORDER:
                fun.accept(counter, v);
                counter.increment();

                if (this.left != null) {
                    this.left.traverseOrdered(counter, fun, mapper, order);
                }

                if (this.right != null) {
                    this.right.traverseOrdered(counter, fun, mapper, order);
                }

                break;
            case POST_ORDER:
                if (this.right != null) {
                    this.right.traverseOrdered(counter, fun, mapper, order);
                }

                fun.accept(counter, v);
                counter.increment();

                if (this.left != null) {
                    this.left.traverseOrdered(counter, fun, mapper, order);
                }

                break;
            case LEVEL_ORDER:
                var list = new LinkedList<TreeNode<T>>();
                list.add(this);

                TreeNode<T> n;
                while ((n = list.poll()) != null) {
                    fun.accept(counter, mapper.apply(n));
                    counter.increment();

                    if (n.left != null) {
                        list.add(n.left);
                    }

                    if (n.right != null) {
                        list.add(n.right);
                    }
                }

                break;
            default:
                break;
        }
    }

    public <V> void traverse(Consumer<V> fun, Function<TreeNode<T>, V> mapper, Order order) {
        this.traverseOrdered(new Counter(), (_, v) -> fun.accept(v), mapper, order);
    }

    public void traverseValue(Consumer<T> fun, Order order) {
        this.traverse(fun, TreeNode::getValue, order);
    }

    public void traverseNode(Consumer<TreeNode<T>> fun, Order order) {
        this.traverse(fun, n -> n, order);
    }

    public TreeNode<T> traverseMatch(@NonNull T v) {
        if (v.equals(this.value)) {
            return this;
        }

        if (this.left != null) {
            var ret = this.left.traverseMatch(v);
            if (ret != null) {
                return ret;
            }
        }

        if (this.right != null) {
            var ret = this.right.traverseMatch(v);
            return ret;
        }

        return null;
    }

    protected TreeNode<T> insertLeft(@NonNull T v) {
        if (this.left == null) {
            this.left = this.create(v);
            this.left.level = this.level + 1;
            this.left.parent = this;

            return this.left;
        }

        return this.left.insertChild(v);
    }

    protected TreeNode<T> insertRight(@NonNull T v) {
        if (this.right == null) {
            this.right = this.create(v);
            this.right.level = this.level + 1;
            this.right.parent = this;

            return this.right;
        }

        return this.right.insertChild(v);
    }

    public TreeNode<T> insertChild(@NonNull T v) {
        if (this.chooseLeft(v)) {
            return this.insertLeft(v);
        }

        return this.insertRight(v);
    }

    protected TreeNode<T> replace(@NonNull T v) {
        try {
            var newNode = (TreeNode<T>) this.clone();
            newNode.value = v;
            // newNode.left = this.left;
            // newNode.right = this.right;
            // newNode.parent = this.parent;

            if (this.left != null) {
                this.left.parent = newNode;
            }
            if (this.right != null) {
                this.right.parent = newNode;
            }

            if (this.parent != null) {
                if (this.parent.left == this) {
                    this.parent.left = newNode;
                }
                if (this.parent.right == this) {
                    this.parent.right = newNode;
                }
            }

            return newNode;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected abstract TreeNode<T> create(T v);

    protected abstract boolean chooseLeft(T v);
}
