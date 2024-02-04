package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

public class Easy543_DiameterOfBinaryTree {

    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        traverse(root);
        maxDepth(root);
        return maxDiameter;

    }

    /**
     * 这个解法是正确的，但是运行时间很长，原因也很明显，traverse 遍历每个节点的时候还会调用递归函数 maxDepth，
     * 而 maxDepth 是要遍历子树的所有节点的，所以最坏时间复杂度是 O(N^2)。
     * 这就出现了刚才探讨的情况，前序位置无法获取子树信息，所以只能让每个节点调用 maxDepth 函数去算子树的深度。
     * 那如何优化？我们应该把计算「直径」的逻辑放在后序位置，准确说应该是放在 maxDepth 的后序位置，
     * 因为 maxDepth 的后序位置是知道左右子树的最大深度的。
     *
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        traverse(root.left);
        traverse(root.right);
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
    }
}
