package SkillCategory.tree.compeleted;

import SkillCategory.tree.TreeNode;

/**
 * @author ywxie
 * @date 2022/5/31 20:32
 * @describe 完全二叉树
 */
public class Solution {
    /**
     * 222. 完全二叉树的节点个数
     * 递归的次数 x 每次递归的时间复杂度，递归次数就是树的高度 O(logN)，
     * 每次递归所花费的时间就是 while 循环，需要 O(logN)，所以总体的时间复杂度是 O(logN*logN)。
     */
    public int countNodes(TreeNode root) {
        TreeNode left = root, right = root;
        // 沿最左侧和最右侧分别计算高度
        int hl = 0, hr = 0;
        while (left != null){
            left = left.left;
            hl++;
        }
        while (right != null){
            right = right.right;
            hr++;
        }
        // 如果左右侧计算的高度相同，则是一棵满二叉树
        // 由于完全二叉树的性质，其子树一定有一棵是满的，所以一定会触发 hl == hr，只消耗 O(logN) 的复杂度而不会继续递归。
        if (hl == hr) {
            return (int)Math.pow(2, hl) - 1;
        }
        // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
