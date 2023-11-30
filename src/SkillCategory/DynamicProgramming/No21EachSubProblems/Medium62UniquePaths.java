package SkillCategory.DynamicProgramming.No21EachSubProblems;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/26 11:38
 */
public class Medium62UniquePaths {
    /**
     * Solution: Recursion
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param m
     * @param n
     * @return
     */
    int[][] memo;

    public int uniquePaths(int m, int n) {
        // initial memo to remember solved sub-problem to solve overlapping problems
        memo = new int[m + 1][n + 1];
        return recursion(m, n);
    }

    public int recursion(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }
        if (memo[m][n] == 0) {
            memo[m][n] = recursion(m - 1, n) + recursion(m, n - 1);
        }
        return memo[m][n];
    }

    /**
     * Solution: DP
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
