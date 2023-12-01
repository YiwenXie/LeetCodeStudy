package SkillCategory.DynamicProgramming.子序列问题;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/1 18:54
 */
public class Medium72EditDistance {
    /**
     * Solution: memo + recursion
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param word1
     * @param word2
     * @return
     */
    int[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        // initial memo
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return recursion(word1, m - 1, word2, n - 1);
    }

    public int recursion(String s1, int i, String s2, int j) {
        // if i <= -1, it represents s1 had all pass but s2 not, so have to add s2's last letter
        // s2.length = insert operation num = j + 1
        if (i <= -1) {
            return j + 1;
        }
        // if j <= -1, it represents s2 had all pass but s1 not, so have to remove s1's last letter
        // s1.length = delete operation num = j + 1
        if (j <= -1) {
            return i + 1;
        }
        // solve overlapping problems
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // if the letter is same => s1[i] = s2[j]
        if (s1.charAt(i) == s2.charAt(j)) {
            // don't need to do any operation, just compare next letter
            memo[i][j] = recursion(s1, i - 1, s2, j - 1);
        } else {
            // insert operation
            // insert j letter to s1's i index before => s1[i + 1] = s2[j] => continue to compare s1[i] and s2[j - 1]
            int a = recursion(s1, i, s2, j - 1) + 1;
            // delete operation
            // delete i letter to s1 in i index => s1[i] not exit => continue to compare s1[i - 1] and s2[j]
            int b = recursion(s1, i - 1, s2, j) + 1;
            // replace operation
            // replace i letter to j letter => s[i] = s[j] => continue to compare s1[i - 1] and s2[j - 1]
            int c = recursion(s1, i - 1, s2, j - 1) + 1;
            // compare which operation's num is minimum
            memo[i][j] = min(a, b, c);
        }
        return memo[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    /**
     * Solution: memo + recursion
     * Time complexity: O(mn)
     * Space complexity: O(mn)
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // initial dp to save minimum edit num that s1[0..i] to s2[0..j]
        int[][] dp = new int[m + 1][n + 1];
        // create base case
        for (int i = 1; i < m + 1; i++) {
            // the maximum edit num that s1[0..i] to s2[0] is i
            dp[i][0] = i;
        }
        for (int j = 1; j < n + 1; j++) {
            // the maximum edit num that s1[0] to s2[0..j] is j
            dp[0][j] = j;
        }
        // start at 1
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // if the letter is same => s1[i - 1] = s2[j - 1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // don't need to do any operation, the minimum edit num of dp[i][j] = dp[i - 1][j - 1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // insert operation
                    // insert j letter to s1's i index before => s1[i + 1] = s2[j] => continue to compare s1[i] and s2[j - 1]
                    // => dp[i][j - 1] + 1
                    int a = dp[i][j - 1] + 1;
                    // delete operation
                    // delete i letter to s1 in i index => s1[i] not exit => continue to compare s1[i - 1] and s2[j]
                    // => dp[i - 1][j] + 1
                    int b = dp[i - 1][j] + 1;
                    // replace operation
                    // replace i letter to j letter in s1 => s[i] = s[j] => continue to compare s1[i - 1] and s2[j - 1]
                    // => dp[i - 1][j - 1] + 1
                    int c = dp[i - 1][j - 1] + 1;
                    // compare which operation's num is minimum
                    dp[i][j] = min(a, b, c);
                }
            }
        }
        return dp[m][n];
    }
}
