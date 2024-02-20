package SkillCategory.tree.bst.operation;

import SkillCategory.tree.TreeNode;

public class Medium1038_BinarySearchTreeToGreaterSumTree {
    TreeNode pre = null;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.right = bstToGst(root.right);
        if (pre != null) {
            root.val += pre.val;
        }
        pre = root;
        root.right = bstToGst(root.left);
        return root;
    }
}
