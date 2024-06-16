package SkillCategory.DynamicProgramming.背包问题;

import java.util.Arrays;

/**
 * 凑零钱的变种题
 * 先dp再硬币
 * 1. 背包问题。完全背包，无限物品，要装满
 * 2. 确定求排列还是组合。
 * 2.1 排列跟顺序有关，需要让背包经过所有物品计算，所以需要外背包内物品
 * 2.2 组合和顺序无关，外物品内背包
 * 3. 本体求组合
 */
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

    public int numSquares2(int n) {
        // dp[i]
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // 组合
        for (int i = 1; i <= n; i++) {
            int num = i * i;
            for (int j = num; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }
        return dp[n];
    }
}
