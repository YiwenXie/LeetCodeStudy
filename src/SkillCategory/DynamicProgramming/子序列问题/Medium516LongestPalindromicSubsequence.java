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
     * leet code solution
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        // save longestPalindromeSubseq in s[i..j]
        // only if 0 <= i <= j < n, dp[i][j] > 0
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            // one letter,so lps is itself => 1
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // s[i] = s[j], before i、j, lps = i..j's [i + 1..j - 1]
                    // Add these two letters to the beginning and end of the array
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
