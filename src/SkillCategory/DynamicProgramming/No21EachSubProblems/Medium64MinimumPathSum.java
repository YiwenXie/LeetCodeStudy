package SkillCategory.DynamicProgramming.No21EachSubProblems;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/27 20:21
 */
public class Medium64MinimumPathSum {

    /**
     * Solution: DP
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = grid[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
