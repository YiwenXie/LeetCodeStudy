package SkillCategory.DynamicProgramming.子序列问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/7 19:54
 */
public class Medium1312MinimumInsertionStepsToMakeAStringPalindrome {

    public static void main(String[] args) {
        Medium1312MinimumInsertionStepsToMakeAStringPalindrome st = new Medium1312MinimumInsertionStepsToMakeAStringPalindrome();
        String s1 = "mbadm";
        System.out.println("s1: " + s1);
        System.out.println("result: " + st.minInsertions(s1));
        System.out.println("result: " + st.minInsertions2(s1));
    }

    /**
     * 问题转换为：添加最少的字符，使得 s1 和 s2 变成相同的字符串。
     * s1 = s, s2 = revers(s)
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int n = s.length();
        // save minInsertions that s1[i..j] s2[j..i], s2 = conversion of s1
        // problem trans to insert letter to s1 or s2 that make s2 equals to s1
        int[][] dp = new int[n + 1][n + 1];
        String s2 = new StringBuilder(s).reverse().toString();
        // base case
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // insert letter to s1
                    int a = dp[i - 1][j] + 1;
                    // insert letter to s2
                    int b = dp[i][j - 1] + 1;
                    dp[i][j] = Math.min(a, b);
                }
            }
        }
        // because operation is to two s,but it is same str, so num need to / 2
        return dp[n][n] / 2;
    }

    /**
     * 找出和反过来的相同最长的子序列
     * 把不相同的那些字母拿过来插到相同的字母前面或者后面,就是回文啦
     *
     * @param s
     * @return
     */
    public int minInsertions2(String s) {
        int n = s.length();
        // save minInsertions that s1[i..j] s2[j..i], s2 = conversion of s1
        // problem trans to insert letter to s1 or s2 that make s2 equals to s1
        int[][] dp = new int[n + 1][n + 1];
        String s2 = new StringBuilder(s).reverse().toString();
        // base case

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[n][n];
        return n - lcs;
    }
}
