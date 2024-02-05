package SkillCategory.DynamicProgramming.背包问题;

import java.util.Arrays;

public class Medium279_PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int square = j * j;
                if (square <= i) {
                    dp[i] = Math.min(dp[i - square] + 1, dp[i]);
                }
            }
        }
        return dp[n];
    }
}
