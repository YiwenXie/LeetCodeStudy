package SkillCategory.DynamicProgramming.stock;

public class Hard188_BestTimeToBuyAndSellStockIV {
    /**
     * 时间复杂度: O(n * k)，其中 n 为 prices 的长度
     * 空间复杂度: O(n * k)
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2 * k + 1];
        // 除了0以外，偶数就是卖出，奇数就是买入。
        // dp[0][j]当j为奇数的时候都初始化为 -prices[0]
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1;i < n; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // j为偶数是卖、奇数是买的状态。
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[n - 1][2 * k];
    }
}
