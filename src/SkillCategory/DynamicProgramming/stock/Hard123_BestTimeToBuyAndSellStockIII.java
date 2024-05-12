package SkillCategory.DynamicProgramming.stock;

public class Hard123_BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i] represents the max profit in i day
        int[][] dp = new int[n][4];
        // dp[i][0] represents the max profit when first having stock in i day
        // dp[i][1] represents the max profit when first no stock in i day
        // dp[i][2] represents the max profit when second having stock in i day
        // dp[i][3] represents the max profit when second no stock in i day

        // base case
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < n; i++) {
            // 1. buy first stock and in i day
            // 2. remain first having stock before i day
            dp[i][0] = Math.max(-prices[i], dp[i - 1][0]);
            // 1. having stock before i day so can sell it firstly in i day
            // 2. remain first no stock before i day
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
            // 1. sold first stock before i day and buy second stock in i day
            // 2. remain second having stock before i day
            dp[i][2] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2]);
            // 1. having second stock before i day and sell second stock in i day
            // 2. remain second no stock before i day
            dp[i][3] = Math.max(dp[i - 1][2] + prices[i], dp[i - 1][3]);
        }
        return dp[n - 1][3];
    }
}
