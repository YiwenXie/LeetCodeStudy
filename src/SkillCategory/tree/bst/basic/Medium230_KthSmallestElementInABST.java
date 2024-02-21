package SkillCategory.tree.bst.basic;

import SkillCategory.tree.TreeNode;

public class Medium230_KthSmallestElementInABST {
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        recursion(root, k);
        return result;
    }

    int count = 0;

    public void recursion(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        recursion(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        recursion(root.right, k);
    }
}
