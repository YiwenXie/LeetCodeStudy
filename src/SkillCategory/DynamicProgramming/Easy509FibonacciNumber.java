package SkillCategory.DynamicProgramming;

/**
 * @author Yiwen Xie
 * @description DP 递归 Steps:
 * *                  0. memo initial
 * *                  1. base case
 * *                  2. memo -> solve overlapping sub-problems
 * *                  3. status transfer formula
 * @date 2023/11/5 8:38
 */
public class Easy509FibonacciNumber {

    /**
     * recursion
     * 递归算法的时间复杂度怎么计算？就是用子问题个数乘以解决一个子问题需要的时间。
     * Time complexity: O(2^n) because overlapping problems
     * Space complexity: O(2n)
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * memo's recursion（solve overlapping sub-problems）
     * Time complexity: O(n) no overlapping
     * space complexity:O(2n) recursion
     *
     * @param n
     * @return
     */
    public int fibOfRecursion(int n) {
        // n = 1 memo[1] -> n = n memo[n]
        int[] memo = new int[n + 1];
        return memoRecursion(memo, n);
    }

    public int memoRecursion(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }
        // solve overlapping
        if (memo[n] != 0) {
            return memo[n];
        }
        // status transfer formula
        return memo[n] = memoRecursion(memo, n - 1) + memoRecursion(memo, n - 2);
    }

    /**
     * Solution: DP
     * Time complexity: O(n)
     * Space complexity: O(n) no recursion
     *
     * @param n
     * @return
     */
    public int fibOfDP(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            // down to up
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Solution: Optimal DP
     * Time complexity: O(n)
     * Space complexity: O(1) no array
     *
     * @param n
     * @return
     */
    public int fibOfOptimalDP(int n) {
        if (n == 0 || n == 1) {
            // base case
            return n;
        }
        // f(n - 1)
        int dp_i_1 = 1;
        // f(n - 2)
        int dp_i_2 = 0;
        for (int i = 2; i < n + 1; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }


}
