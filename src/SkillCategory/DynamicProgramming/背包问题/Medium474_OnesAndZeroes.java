package SkillCategory.DynamicProgramming.背包问题;

public class Medium474_OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        // backpack have two-dimensional weight
        int[][] dp = new int[m + 1][n + 1];
        // iterate over items
        for (String str: strs) {
            int zeroNums = 0;
            int oneNums = 0;
            // calculate zero and one nums in str
            for (char c: str.toCharArray()) {
                if('0' == c) {
                    zeroNums++;
                } else {
                    oneNums++;
                }
            }
            // iterate over backpack from big to small
            // because every item only can put in once
            for (int i = m; i >= zeroNums; i--) {
                for (int j = n; j >= oneNums; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNums][j - oneNums] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
