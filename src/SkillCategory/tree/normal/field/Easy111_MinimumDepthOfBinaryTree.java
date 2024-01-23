package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy111_MinimumDepthOfBinaryTree {

    int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traversal(root, 1);
        return minDepth;
    }

    private void traversal(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            minDepth = Math.min(depth, minDepth);
            return;
        }
        if (root.left != null) {
            traversal(root.left, depth + 1);
        }
        if (root.right != null) {
            traversal(root.right, depth + 1);
        }
    }
}
