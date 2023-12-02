package SkillCategory.DynamicProgramming.子序列问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/2 10:18
 */
public class Medium712MinimumASCIIDeleteSumForTwoStrings {

    public static void main(String[] args) {
        Medium712MinimumASCIIDeleteSumForTwoStrings medium712 = new Medium712MinimumASCIIDeleteSumForTwoStrings();
        String s1 = "delete";
        String s2 = "leet";
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("result: " + medium712.minimumDeleteSum(s1, s2));
    }

    /**
     * Solution: DP 子序列
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // initial dp to save minimum ASCII value that s1[0..i] + s2[0..j]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i < m + 1; i++) {
            // the ASCII value that delete s1[0..i] to empty value same as s2
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j < n + 1; j++) {
            // the ASCII value that delete s2[0..j] to empty value same as s1
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // delete s1 not delete s2
                    int a = dp[i - 1][j] + s1.charAt(i - 1);
                    // delete s2 not delete s1
                    int b = dp[i][j - 1] + s2.charAt(j - 1);
                    // delete s1 and delete s2, don't need to think about it, because two > one
//                    int c = dp[i - 1][j - 1] + s1.charAt(i - 1) + s2.charAt(j - 1);
                    dp[i][j] = Math.min(a, b);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Solution: DP 求最长公共子序列的ASCII
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // total ASCII value of s1 + s2
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += s1.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            sum += s2.charAt(i);
        }
        if (sum == 0) {
            return 0;
        }
        // initial dp to save maximum ASCII value that ASCII value of same string of s1 and s2
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // letter is same so add letter's ASCII value to pre-sub-problem
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    // letter is different so compare letter's ASCII value to delete s1 or delete s2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                // save maximum ASCII value
                max = Math.max(max, dp[i][j]);
            }
        }
        // max just one char value so there need to * 2
        return sum - 2 * max;
    }
}
