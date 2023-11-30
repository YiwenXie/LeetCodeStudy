package SkillCategory.DynamicProgramming.No11ConstSmallerProblems;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/7 21:29
 */
public class Easy746MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }
}
