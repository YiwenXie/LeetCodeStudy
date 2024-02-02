package SkillCategory.tree.bst.basic;

import SkillCategory.tree.TreeNode;

public class Medium98_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recursion(root, null, null);
    }

    public boolean recursion(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return recursion(root.left, min, root) &&
                recursion(root.right, root, max);
    }
}
