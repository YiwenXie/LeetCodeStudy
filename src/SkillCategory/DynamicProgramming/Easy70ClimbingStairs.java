package SkillCategory.DynamicProgramming;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/4 21:56
 */
public class Easy70ClimbingStairs {
    /**
     * Error Solution: recursion
     * when n == 45, exceed time limit
     * Time complexity: O(2^n)
     * space complexity: 0(2n)
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * Solution: memo + recursion
     * Time complexity: O(n)
     * space complexity: 0(n)
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        // memo initial
        int[] memo = new int[n + 1];
        return fMemoRecursion(memo, n);
    }

    public int fMemoRecursion(int[] memo, int n) {
        // f(2) = f(1) + f(0) = 1 + 1 = 2
        if (n <= 1) {
            return 1;
        }
        // or
//        if (n == 1 || n == 2){
//            return n;
//        }
        if (memo[n] != 0) {
            return memo[n];
        }
        return memo[n] = fMemoRecursion(memo, n - 1) + fMemoRecursion(memo, n - 2);
    }

    /**
     * Solution: dp
     * Time complexity: O(n)
     * space complexity: 0(n)
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Solution: optimal dp
     * Time complexity: O(n)
     * space complexity: 0(1)
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // f(n - 1)
        int dp_i_1 = 1;
        // f(n - 2)
        int dp_i_2 = 1;
        for (int i = 2; i < n + 1; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
