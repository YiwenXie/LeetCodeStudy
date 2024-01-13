package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Easy100_SameTree {
    /**
     * Solution: recursion
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q == null) {
            return false;
        } else if (q != null && p == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    /**
     * iterative method (queue)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            } else if (left != null && right == null) {
                return false;
            } else if (right != null && left == null) {
                return false;
            } else if (left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.left);
            queue.add(left.right);
            queue.add(right.right);
        }
        return true;
    }

    /**
     * iterative method (stack)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree3(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            } else if (left != null && right == null) {
                return false;
            } else if (right != null && left == null) {
                return false;
            } else if (left.val != right.val) {
                return false;
            }
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
            stack.push(left.left);
        }
        return true;
    }
}
