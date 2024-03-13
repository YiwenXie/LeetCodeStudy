package SkillCategory.DynamicProgramming.stock;

public class Medium122_BestTimeToBuyAndSellStockII {
    /**
     * Solution: DP - normal
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i] represents the max profit in i day
        int[][] dp = new int[n][2];
        // dp[i][0] represents the max profit when having stock in i day
        //      1. no stock so can buy stock and in i day
        //      2. having stock before i day and not to sell it
        // dp[i][1] represents the max profit when no stock in i day
        //      1. having stock so can sell it and in i day
        //      2. no stock before i day and not to buy i price
        // base case
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    /**
     * Solution: DP - optimal space
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][1] - prices[i], dp[(i - 1) % 2][0]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][0] + prices[i], dp[(i - 1) % 2][1]);
        }
        return Math.max(dp[(n - 1) % 2][0], dp[(n - 1) % 2][1]);
    }

    /**
     * Solution: DP - optimal space
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int have = -prices[0];
        int empty = 0;
        for (int i = 1; i < n; i++) {
            have = Math.max(empty - prices[i], have);
            empty = Math.max(have + prices[i], empty);
        }
        return Math.max(have, empty);
    }
}
