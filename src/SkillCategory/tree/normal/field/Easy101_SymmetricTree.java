package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Easy101_SymmetricTree {
    /**
     * iterative method (queue)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
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
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    /**
     * iterative method (stack)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.right);
        stack.push(root.left);
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
            stack.push(right.left);
            stack.push(left.right);
            stack.push(right.right);
            stack.push(left.left);
        }
        return true;
    }

    /**
     * Solution: recursion
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        return toCompare(root.left, root.right);
    }

    private boolean toCompare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right == null) {
            return false;
        } else if (right != null && left == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        boolean outside = toCompare(left.left, right.right);
        boolean inside = toCompare(left.right, right.left);
        boolean isSame = outside && inside;
        return isSame;
    }


}
