package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return traversal(root, 0, targetSum);
    }

    private boolean traversal(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return false;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }
        boolean left = traversal(root.left, sum, targetSum);
        boolean right = traversal(root.right, sum, targetSum);
//        if (root.left == null && root.right == null) {
//            return sum == targetSum;
//        }
        return left || right;
    }
}
