package SkillCategory.DynamicProgramming.背包问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/14 19:02
 */
public class Medium494_TargetSum {
    /**
     * Solution: dp
     * left - right = target -> right = left - target
     * left + right = sum -> left = sum - right = sum - left + target ->
     * left = (sum + target) / 2
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        if (Math.abs((sum + target)) % 2 != 0) {
            return 0;
        }
        int left = Math.abs((sum + target)) / 2;
        int[] dp = new int[left + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = left; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[left];
    }
}
