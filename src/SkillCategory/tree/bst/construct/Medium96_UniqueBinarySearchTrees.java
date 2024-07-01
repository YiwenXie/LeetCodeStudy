package SkillCategory.tree.bst.construct;

/**
 * 我们不妨就假设我们拿到了一个中序遍历的数组nums = [1,2,3,4,5,6,7]，来思考一个这样的数组能延伸出多少种二叉搜索树。
 * 首先，对于数组中的每一个元素，都有可能成为二叉树最顶部的root节点，例如上图中，是nums[4]这个值，即5，充当了root节点。
 * 还拿5这个节点为例，即上图，其左边有四个节点，右边有两个节点。
 * 对于左边的四个节点，假设能延伸出 n 种二叉搜索树子树，
 * 对于右边的两个节点，假设能延伸出 m 种二叉搜索树子树。
 * 则以5为root节点时的二叉搜索树总数为 m*n
 * 这样我们遍历刚刚的nums数组，以值i（注意不是下标）当做根节点，其左边有i-1个节点，右边有n-i个节点，
 * 计算出可能的二叉搜索树数量.添加到总结果里即可
 */
public class Medium96_UniqueBinarySearchTrees {
    int[][] memo;
    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return recursion(1, n);
    }

    private int recursion(int lo, int hi) {
        if (lo > hi) {
            // why return 1 ?
            //
            return 1;
        }
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }
        int result = 0;
        for (int i = lo; i <= hi; i++) {
            // make i become root
            // [lo, i - 1] is left child range
            int left = recursion(lo, i - 1);
            // [i, hi] is right child range
            int right = recursion(i + 1, hi);
            result += left * right;
        }
        memo[lo][hi] = result;
        return result;
    }

    public int numTrees2(int n) {
        // dp[i] indicates how many combination ways when root node is i
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        // iterate over n (total node num)
        for (int i = 2; i <= n; i++) {
            // assume j is root
            for (int j = 1; j <= i; j++) {
                // when j is root
                // left node combination ways is dp[j - 1]
                // right node combination ways is dp[j - 1]
                // so j's is dp[j - 1] * dp[i - j]
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
