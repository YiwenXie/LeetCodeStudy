package SkillCategory.DynamicProgramming.子序列问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/6 19:30
 */
public class Medium516LongestPalindromicSubsequence {
    /**
     * Solution: DP
     * problem -> the longest common subsequence for s1 and s2
     * s1 = s, s2 = conversion of s
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // save lcs length for s1[0..i] and s2[0..j]
        int[][] dp = new int[n + 1][n + 1];
        // base case all zero
        // left to right traversing s1
        for (int i = 1; i < n + 1; i++) {
            // right to left traversing s2
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i - 1) == s.charAt(j)) {
                    dp[i][j] = dp[i - 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[n][0];
    }

    /**
     * correct solution
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        // dp[i][j] save lps of s[i..j]
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            // letter is s[i] so it's lps is itself letter's length which is one
            dp[i][i] = 1;
        }

        // only if 0 <= i <= j < n, dp[i][j] > 0
        // so traversal order must be down to up
        // iterate over s from right to left
        for (int i = n - 1; i < n; i++) {
            // iterate over s from i + 1 to right
            for (int j = i + 1; j < n; j++) {
                // can plus lps's length in s[i,j]
                if (s.charAt(i) == s.charAt(j)) {
                    // s[i] = s[j], before i、j, lps = i..j's [i + 1..j - 1]
                    // Add these two letters to the beginning and end of the array
                    // lcs's length = the result before dp[i][j] plus their letter's num
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // put one letter in dp separately to find out which operation can make s[i,j] to lps
                    // if put s[j] letter, dp[i][j] = dp[i + 1][j]
                    // if put s[i] letter, dp[i][j] = dp[i][j - 1]
                    // get the maximum value
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                // dp[i][j] depends on dp[i + 1][j - 1], dp[i + 1][j], dp[i][j - 1]
            }
        }
        return dp[0][n - 1];
    }
}
