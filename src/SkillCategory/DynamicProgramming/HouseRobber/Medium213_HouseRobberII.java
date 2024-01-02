package SkillCategory.DynamicProgramming.HouseRobber;

public class Medium213_HouseRobberII {
    public static void main(String[] args) {
        Medium213_HouseRobberII medium213_houseRobberII = new Medium213_HouseRobberII();
        int[] nums = new int[]{1, 2, 1, 1};
        System.out.println("result: " + medium213_houseRobberII.rob(nums));
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // start 0, end n - 2 考虑是否偷第一间房，但绝对不偷最后一间房
        // start 1, end n - 1 决定不偷第一间房，但考虑是否偷最后一间房
        return Math.max(toRob(nums, 0, n - 2), toRob(nums, 1, n - 1));
    }

    private int toRob(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }
}
