package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy110_BalancedBinaryTree {


    public boolean isBalanced(TreeNode root) {
        return recursion(root) != -1;
    }

    private int recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recursion(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recursion(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
