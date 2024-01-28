package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy222_CountCompleteTreeNodes {
    /**
     * Recursion
     * Time complexity: O(n)
     * Space complexity: O(log n)
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    /**
     * 针对完全二叉树的解法
     * 满二叉树的结点数为：2^depth - 1
     * Time complexity: O(n)
     * Space complexity: O(log n)
     *
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        while (left != null) {
            left = left.left;
            leftHeight++;
        }

        while (right != null) {
            right = right.right;
            rightHeight++;
        }

        if (leftHeight == rightHeight) {
            return (2 << leftHeight) - 1;
//            return (int) (Math.pow(2, leftHeight) - 1);
        }
        return countNodes2(root.left) + countNodes2(root.right) + 1;
    }
}
