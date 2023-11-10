package SkillCategory.DynamicProgramming;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/10 21:23
 */
public class Medium322CoinChange {

    /**
     * Solution: DP
     * Time complexity: O(n * coins.length)
     * Space complexity: O(n)
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // initial dp array
        // dp's data core is return what, change what
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins
            ) {
                // if coin > i(amount),that has a case is this coin not fit
                if (i < coin) {
                    continue;
                }
                // if we know the number of coins that meet amount less than question's amount
                // So we can through the smaller amount's answer to get bigger amount's answer
                // like dp[i - coin] + 1, [i - coin] is answer number that now amount subtract now coin
                // [i - coin] + 1 is now answer number that smaller amount's answer plus now coin(this for each coin, so + 1)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * Solution: Recursion
     * Time complexity: O(n * coins.length)
     * Space complexity: O(n)
     *
     * @param coins
     * @param amount
     * @return
     */
    int[] memo;

    public int coinChange2(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        return recursion322(coins, amount);
    }

    public int recursion322(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins
        ) {
            // get sub-problems result
            int subProblem = recursion322(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            // select optimal solution
            result = Math.min(result, subProblem + 1);
        }
        memo[amount] = result == Integer.MAX_VALUE ? -1 : result;
        return memo[amount];
    }
}
