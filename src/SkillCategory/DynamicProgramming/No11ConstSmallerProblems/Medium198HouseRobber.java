package SkillCategory.DynamicProgramming.No11ConstSmallerProblems;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/6 21:40
 */
public class Medium198HouseRobber {
    /**
     * Solution: DP
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // suppose to rob n houses
        int n = nums.length;
        // init dp array
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n + 1; i++) {
            // if rob ith house : dp[i - 2] + nums[i - 1] --> ...+ (n - 2) + n
            // if not rob ith house, rob (i - 1)th house: dp[i - 1] --> (n - 3) + (n - 1)
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[n];
    }

    /**
     * Solution: DP
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // suppose to rob n houses
        int n = nums.length;
        int sum = 0;
        int a = 0;
        int b = nums[0];
        for (int i = 2; i < n + 1; i++) {
            sum = Math.max(a + nums[i - 1], b);
            a = b;
            b = sum;
        }
        return sum;
    }
}
