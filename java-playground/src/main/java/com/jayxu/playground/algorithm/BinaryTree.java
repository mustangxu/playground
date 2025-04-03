/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.io.Serial;

import javax.annotation.Nonnull;

import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author jayxu
 */
@NoArgsConstructor
public class BinaryTree<T extends Comparable<T>> extends Tree<T> {
    @Serial
    private static final long serialVersionUID = -2587073973029359971L;

    public BinaryTree(Order order) {
        super(order);
    }

    public static class BinaryTreeNode<T extends Comparable<T>> extends TreeNode<T> {
        @Serial
        private static final long serialVersionUID = 3051936643420669043L;

        public BinaryTreeNode(T v) {
            super(v);
        }

        @Override
        public BinaryTreeNode<T> traverseMatch(@Nonnull T v) {
            if (this.value.equals(v)) {
                return this;
            }

            if (v.compareTo(this.value) < 0 && this.left != null) {
                return (BinaryTreeNode<T>) this.left.traverseMatch(v);
            }

            if (v.compareTo(this.value) > 0 && this.right != null) {
                return (BinaryTreeNode<T>) this.right.traverseMatch(v);
            }

            return null;
        }

        @Override
        protected BinaryTreeNode<T> create(T v) {
            return new BinaryTreeNode<>(v);
        }

        @Override
        protected boolean chooseLeft(T v) {
            return v.compareTo(this.value) <= 0;
        }

    }

    @Override
    public boolean add(@NonNull T e) {
        this.size++;

        if (this.root == null) {
            this.root = new BinaryTreeNode<>(e);
        } else {
            var node = this.root.insertChild(e);
            this.depth = Math.max(node.level, this.depth);
        }

        return true;
    }

    @Override
    public boolean remove(@NonNull Object o) {
        if (this.root == null) {
            return false;
        }

        var node = this.root.traverseMatch((T) o);
        if (node == null) { // not include
            return false;
        }

        if (this.size == 1) { // root only
            this.clear();
            return true;
        }

        if (node.left == null || node.right == null) {
            var child = node.left == null ? node.right : node.left;
            var parent = node.parent;

            if (child != null) {
                child.parent = parent;
            }

            if (node.isRoot()) {
                this.root = child;
            } else if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            var left = node.right.left;

            if (left == null) {
                node.right.left = node.left;
                node.left.parent = node.right;
            } else {
                while (left.left != null) {
                    left = left.left;
                }

                var newNode = node.replace(left.value);
                if (node.isRoot()) {
                    this.root = newNode;
                }

                left.parent.left = null; // delete
            }
        }

        this.size--;
        this.recalculateLevelsNDepth();
        return true;
    }

}
