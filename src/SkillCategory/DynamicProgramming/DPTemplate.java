package SkillCategory.DynamicProgramming;

/**
 * @author Yiwen Xie
 * @description DP requirements:
 * 1.optimal substructure: optimal solutions to the sub-problems
 * 2.overlapping sub-problems:compute only once and store for future use, reduce time complexity
 * 2.1 if sub-problems do not overlap -> divide and conquer
 * 3.No-after effect:sub-problems optimal solution will not change bigger problem optimally
 * different between recursion and DP:
 * 1.recursion is up to down to get base case
 * 2.dp is down to up through solve sub-problems
 * Solution Template:
 * Input: O(n)
 * Sub-problems: O(n)
 * Each sub-problem depends on O(1) smaller problems
 * Time complexity: O(n)
 * Space complexity: O(n) -> O(1)
 * dp[i] := solution of A[1->i] // prefix
 * @date 2023/11/4 21:48
 */
public class DPTemplate {

    /**
     * example: 70
     *
     * @param n
     * @return
     */
    public int dp(int n) {
        // base case
        if (n == 1 || n == 2) {
            return n;
        }
        // dp  formula
        int[] dp = new int[n + 1];
        // sub-problems optimal solution
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            // down to up
            // bigger problem optimally
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // solved bigger problem
        return dp[n];

    }


}
