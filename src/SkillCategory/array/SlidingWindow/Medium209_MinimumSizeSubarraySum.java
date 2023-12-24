package SkillCategory.array.SlidingWindow;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/11 19:34
 */
public class Medium209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int windowSum = 0;
        int len = Integer.MAX_VALUE;
        while (right < nums.length) {
            int r = nums[right++];
            windowSum += r;
            while (windowSum >= target) {
                len = Math.min(len, right - left);
                int l = nums[left++];
                windowSum -= l;
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
