package SkillCategory.tree.bst.operation;

import SkillCategory.tree.TreeNode;

public class Medium538_ConvertBSTToGreaterTree {
    TreeNode pre = null;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.right = convertBST(root.right);
        if (pre != null) {
            root.val += pre.val;
        }
        pre = root;
        root.left = convertBST(root.left);
        return root;
    }
}
