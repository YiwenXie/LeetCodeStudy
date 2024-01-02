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
        int left = Math.abs(sum + target) / 2;
        // dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
        int[] dp = new int[left + 1];
        // 在初始化的时候dp[0] 一定要初始化为1，因为dp[0]是在公式中一切递推结果的起源，如果dp[0]是0的话，递推结果将都是0。
        dp[0] = 1;
        for (int num : nums) {
            // 必须倒序。倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
            for (int i = left; i >= num; i--) {
                // 只要搞到nums[i]，凑成dp[j]就有dp[j - nums[i]] 种方法。也就是把 所有的 dp[j - nums[i]] 累加起来
                dp[i] += dp[i - num];
            }
        }
        return dp[left];
    }
}
