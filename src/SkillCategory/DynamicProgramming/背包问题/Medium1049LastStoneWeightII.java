package SkillCategory.DynamicProgramming.背包问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/10 14:54
 */
public class Medium1049LastStoneWeightII {

    /**
     * Solution: DP
     * Time complexity: O(n * sum / 2)
     * Space complexity: O(n)
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // 本题其实就是尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题了。
        int target = sum / 2;
        // capacity is i, dp[i] = the maximum weight that backpack can afford
        int[] dp = new int[target + 1];
        dp[0] = 0;
        // every stone can only use once
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        // 那么分成两堆石头，一堆石头的总重量是dp[target]，另一堆就是sum - dp[target]。
        // 在计算target的时候，target = sum / 2 因为是向下取整，所以sum - dp[target] 一定是大于等于dp[target]的。
        // 那么相撞之后剩下的最小石头重量就是 (sum - dp[target]) - dp[target]。
        return sum - dp[target] - dp[target];
    }
}
