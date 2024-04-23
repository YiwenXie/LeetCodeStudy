package SkillCategory.DynamicProgramming.背包问题;

public class Easy70_ClimbingStairsUpgrade {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * 输入描述：输入共一行，包含两个正整数，分别表示n, m
     * 输出描述：输出一个整数，表示爬到楼顶的方法数。
     * 输入示例：3 2
     * 输出示例：3
     * 提示：
     * 当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n, int m) {
        // n represents capacity
        // m represents items weigh [1, 2, ... m]
        // the number of ways that can fill the capacity using items
        // every item's number is infinite

        // dp[i] represents the number of ways that can fill the capacity using all items when capacity is i
        int[] dp = new int[n + 1];
        // base case
        // 既然递归公式是 dp[i] += dp[i - j]，那么dp[0] 一定为1，dp[0]是递归中一切数值的基础所在，如果dp[0]是0的话，其他数值都是0了。
        //下标非0的dp[i]初始化为0，因为dp[i]是靠dp[i-j]累计上来的，dp[i]本身为0这样才不会影响结果
        dp[0] = 1;
        // iterate over capacity
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j > i) {
                    break;
                }
                // dp[i - j] represents i - j capacity have x ways
                // so dp[i] += dp[i - j]
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }
}
