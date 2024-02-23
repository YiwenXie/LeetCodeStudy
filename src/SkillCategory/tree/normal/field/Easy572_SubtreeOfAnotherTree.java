package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy572_SubtreeOfAnotherTree {
    /**
     * Solution: recursion
     * Time Complexity: O(n)
     * Space Complexity: O(n^3)
     *
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q == null) {
            return true;
        } else if (q != null && p == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
