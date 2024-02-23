package SkillCategory.DynamicProgramming.子序列问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/3 9:14
 */
public class Medium53_MaximumSubarray {

    /**
     * Solution: slide window
     * 1. nums 中全是负数的时候，此时算法是可以得到正确答案的。
     * 2. nums 中有正有负，这种情况下元素和最大的那个子数组一定是以正数开头的
     * （以负数开头的话，把这个负数去掉，就可以得到和更大的子数组了，与假设相矛盾）。
     * 那么此时我们需要穷举所有以正数开头的子数组，计算他们的元素和，找到元素和最大的那个子数组。
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @return 最大子数组和
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            // 扩大窗口并更新窗口内的元素和
            int a = nums[right++];
            windowSum += a;
            // 更新答案
            maxSum = Math.max(windowSum, maxSum);
            // 判断窗口是否要收缩
            while (windowSum < 0) {
                // 缩小窗口并更新窗口内的元素和
                int b = nums[left++];
                windowSum -= b;
            }
        }
        return maxSum;
    }

    /**
     * Solution: DP
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        // 重点：以 nums[i] 为结尾的「最大子数组和」为 dp[i]。
        int[] dp = new int[n];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        // 得到 nums 的最大子数组
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i], result);
        }
        return result;
    }

    /**
     * Solution: DP2
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        // base case
        // 第一个元素前面没有子数组
        int a = nums[0];
        for (int i = 1; i < n; i++) {
            int b = Math.max(nums[i], a + nums[i]);
            a = b;
            result = Math.max(result, b);
        }
        return result;
    }

    /**
     * Solution: prefix sum
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public int maxSubArray4(int[] nums) {
        int n = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        // 构造 nums 的前缀和数组
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 维护 minVal 是 preSum[0..i] 的最小值
            minVal = Math.min(minVal, prefixSum[i]);
            // 以 nums[i] 结尾的最大子数组和就是 preSum[i+1] - min(preSum[0..i])
            res = Math.max(res, prefixSum[i + 1] - minVal);
        }
        return res;
    }
}
