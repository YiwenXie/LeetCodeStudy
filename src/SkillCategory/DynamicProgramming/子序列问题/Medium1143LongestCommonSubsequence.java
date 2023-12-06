package SkillCategory.DynamicProgramming.子序列问题;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/4 19:17
 */
public class Medium1143LongestCommonSubsequence {
    /**
     * Solution: DP
     * 参照712题转换求最大公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                    // s1[i - 1] and s2[j - 1] is definitely in LCS
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    // s[i - 1] or s2[j - 1] is not in LCS
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Solution: recursion
     * @param text1
     * @param text2
     * @return
     */
    int[][] memo;
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return recursion(text1, 0, text2, 0);
    }

    public int recursion(String text1, int i, String text2, int j){
        if (i >= text1.length() || j >= text2.length()){
            return 0;
        }
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        if (text1.charAt(i) == text2.charAt(j)){
            return recursion(text1, i + 1, text2, j + 1) + 1;
        }
        int a = recursion(text1, i + 1, text2, j);
        int b = recursion(text1, i, text2, j + 1);
        memo[i][j] = Math.max(a, b);
        return memo[i][j];
    }
}
