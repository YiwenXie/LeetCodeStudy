package SkillCategory.DynamicProgramming;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/30 20:08
 */
public class Medium63UniquePathsII {
    /**
     * Solution: DP
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param obstacleGrid
     * @return Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // have obstacle, this path should be abandon
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    continue;
                }
                // base case
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
