package SkillCategory.DynamicProgramming;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/15 19:58
 */
public class Medium300LongestIncreasingSubsequence {
    /**
     * Solution: DP
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // initial dp array
        int[] dp = new int[nums.length];
        // fill dp initial value 1 because shortest subsequence length is 1 that means contains itself
        Arrays.fill(dp, 1);
        // definite final result variable
        int result = Integer.MIN_VALUE;
        // first for to set dp[i]'s result
        for (int i = 0; i < n; i++) {
            // second for to get dp[i]'s final solution
            for (int j = 0; j < i; j++) {
                // how to get dp[i] in dp[1...n],because requirement is increasing subsequence
                // 1.get num[j] smaller than num[i] before index i,
                // so that dp[j]'s increasing subsequence + num[i] definitely also is increasing subsequence
                // 2.compares dp[i]'s length and new length
                // 3.then continue to get next num[j] that smaller than num[i]
                // 4.so all  dp[i]'s value is the longest subsequence length for num[i] of end
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // get longest subsequence length
            result = Math.max(dp[i], result);
        }
        return result;
    }
}
