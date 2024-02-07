package SkillCategory.tree.normal.construct;

import SkillCategory.tree.TreeNode;


public class Medium654_MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return recursion(nums, 0, nums.length);
    }

    private TreeNode recursion(int[] inorder, int inStart, int inEnd) {
        if (inStart >= inEnd) {
            return null;
        }
        int rootVal = Integer.MIN_VALUE;
        int rootIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (rootVal < inorder[i]) {
                rootVal = inorder[i];
                rootIndex = i;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = recursion(inorder, inStart, rootIndex);
        root.right = recursion(inorder, rootIndex + 1, inEnd);
        return root;
    }
}
