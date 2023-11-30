package SkillCategory.DynamicProgramming;

/**
 * @author Yiwen Xie
 * @description each sub-problem depends on O(1) sub-problems
 * Input O(mn)  m X n
 * dp[i][j] = solution of (A[1->i][1-j])
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * <p>
 * Template:
 * dp = new int[m + 1][n + 1]
 * for i = 1 to m + 1:
 * for j = 1 to n + 1:
 * dp[i][j] = f(dp[i - 1][j], dp[i][j - 1])
 * return dp[m][n] or max(dp[m]) or min(dp[m])
 * @date 2023/11/30 20:47
 */
public class No21EachSubProblemsDPTemplate {
}
