package SkillCategory.tree.bst.construct;

import SkillCategory.tree.TreeNode;

public class Easy108_ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums, 0, nums.length);
//        return recursion2(nums, 0, nums.length - 1);
    }

    private TreeNode recursion(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursion(nums, lo, mid);
        root.right = recursion(nums, mid + 1, hi);
        return root;
    }

    private TreeNode recursion2(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursion2(nums, lo, mid - 1);
        root.right = recursion2(nums, mid + 1, hi);
        return root;
    }
}
