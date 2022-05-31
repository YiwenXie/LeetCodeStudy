package SkillCategory.tree.bst.construct;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ywxie
 * @date 2022/5/24 20:51
 * @describe
 */
public class Solution {
    /**
     * 96. 不同的二叉搜索树
     * 左子树的组合数和右子树的组合数乘积就是3作为根节点时的 BST 个数。
     * 消除重叠子问题：备忘录
     */
    // 备忘录
    int[][] memo;
    public int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        // 计算闭区间 [1, n] 组成的 BST 个数
        return count(1, n);
    }


    // 定义：闭区间 [lo, hi] 的数字能组成 count(lo, hi) 种 BST
    int count(int lo, int hi){
        // base case
        // 当lo > hi闭区间[lo, hi]肯定是个空区间，也就对应着空节点 null，
        // 虽然是空节点，但是也是一种情况，所以要返回 1 而不能返回 0。
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }
        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // i 的值作为根节点 root
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }

    /**
     * 95. 不同的二叉搜索树 II
     * 明白了上道题构造合法 BST 的方法，这道题的思路也是一样的：
     * 1、穷举root节点的所有可能。
     * 2、递归构造出左右子树的所有合法 BST。
     * 3、给root节点穷举所有左右子树的组合。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return buildTree(1, n);
    }

    List<TreeNode> buildTree(int lo, int hi){
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi){
            res.add(null);
            return res;
        }
        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {
            // 2、递归构造出左右子树的所有合法 BST。
            List<TreeNode> leftList = buildTree(lo, i - 1);
            List<TreeNode> rightList = buildTree(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left: leftList
                 ) {
                for (TreeNode right: rightList
                     ) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
