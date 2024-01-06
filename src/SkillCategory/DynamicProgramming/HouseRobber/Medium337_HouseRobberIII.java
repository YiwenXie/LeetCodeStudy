package SkillCategory.DynamicProgramming.HouseRobber;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class Medium337_HouseRobberIII {
    /**
     * recursion + memo
     * Time complexity: O(n)
     * Space complexity: O(log n)
     *
     * @param root
     * @return
     */
    HashMap<TreeNode, Integer> hashMap = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (hashMap.containsKey(root)) {
            hashMap.get(root);
        }
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right);
        }
        int val2 = rob(root.left) + rob(root.right);
        hashMap.put(root, Math.max(val1, val2));
        return hashMap.get(root);
    }

    /**
     * dp
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        // 0 is not to rob this node
        // 1 is to rob this node
        int[] dp = toDP(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] toDP(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        if (root.left == null && root.right == null) {
            return new int[]{0, root.val};
        }
        int[] left = toDP(root.left);
        int[] right = toDP(root.right);
        int val1 = root.val + left[0] + right[0];
        int val2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{val2, val1};
    }

}
