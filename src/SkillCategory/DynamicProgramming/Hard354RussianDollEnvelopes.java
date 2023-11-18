package SkillCategory.DynamicProgramming;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/18 15:32
 */
public class Hard354RussianDollEnvelopes {
    /**
     * Solution: DP
     * Time complexity: O(n^2) exceed time limit
     * Space complexity: O(n)
     *
     * @param envelopes envelopes[i] = [wi, hi]
     * @return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            // if width is same, can't contain each other, then sort the height in descending order
            // to ensure that there are no multiple envelopes with the same w in the 2D LIS
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            // Sort widths in ascending order, ensure in width dimensions we can fit into each orther
            // then only focus on height dimensions
            return o1[0] - o2[0];
        });
        int result = 1;
        int n = envelopes.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // focus on height dimensions, find the longest increasing subsequence for height
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // save the longest increasing subsequence's length
            result = Math.max(dp[i], result);
        }
        return result;
    }
}
