package SkillCategory.DynamicProgramming;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/21 20:39
 */
public class Medium931MinimumFallingPathSum {
    /**
     * Solution: memo + recursion
     * Time complexity:O(2n)
     * Space complexity: O(n)
     *
     * @param matrix
     * @return
     */
    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        memo = new int[n][n];
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            result = Math.min(result, recursion(matrix, n - 1, i));
        }
        return result;
    }

    public int recursion(int[][] matrix, int i, int j) {
        // out of index
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length) {
            return Integer.MAX_VALUE;
        }
        // down to first row
        if (i == 0) {
            memo[i][j] = matrix[i][j];
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        // the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
        memo[i][j] = matrix[i][j] + min(recursion(matrix, i - 1, j - 1), recursion(matrix, i - 1, j), recursion(matrix, i - 1, j + 1));
        return memo[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Solution: DP
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    if (j - 1 < 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                    } else if (j + 1 >= n) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1])) + matrix[i][j];
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(dp[n - 1][i], result);
        }
        return result;
    }
}
