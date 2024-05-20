package SkillCategory.DynamicProgramming.stock;

public class Medium309_BestTimeToBuyAndSellStockWithCooldown {
    /**
     * 状态一：持有股票状态（今天买入股票，或者是之前就买入了股票然后没有操作，一直持有）
     * 不持有股票状态，这里就有两种卖出股票状态
     * 状态二：保持卖出股票的状态（两天前就卖出了股票，度过一天冷冻期。或者是前一天就是卖出股票状态，一直没操作）
     * 状态三：今天卖出股票
     * 状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        // four status : hold stock, not hold stock, not hold stock in cooldown, cooldown
        // can transaction many times but can not buy stock after sell yesterday
        int[][] dp = new int[n][4];
        // hold stock: buy stock at today or maintain old status
        dp[0][0] = -prices[0];
        // not hold stock:
        //  maintain old status
        //  sell stock at two day ago and yesterday is cooldown
        dp[0][1] = 0;
        // not hold stock: sell stock in today
        dp[0][2] = 0;
        // not hold stock: sell stock yesterday so cooldown status
        dp[0][3] = 0;
//        dp[1][0] = Math.max(dp[0][0], dp[0][1] -prices[1]);
//        dp[1][1] = ;
//        dp[1][2] = 0;
//        dp[1][3] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[1];
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(Math.max(dp[n - 1][1], dp[n - 1][2]), dp[n - 1][3]);
    }
}
