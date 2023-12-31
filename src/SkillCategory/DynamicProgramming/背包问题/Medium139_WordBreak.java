package SkillCategory.DynamicProgramming.背包问题;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medium139_WordBreak {
    /**
     * Solution: backtracking 回溯法 代码随想录
     * Time complexity: O(2^n)
     * Space complexity: O(n)
     * @param s
     * @param wordDict
     * @return
     */
    private int[] memo;
    private Set<String> set;
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length()];
        set = new HashSet<>(wordDict);
        return backtracking(s, 0);
    }

    private boolean backtracking(String s, int startIndex) {
        if (startIndex < 0 || startIndex >= s.length()) {
            return true;
        }
        if (memo[startIndex] == -1) {
            return false;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            if (!set.contains(str)) {
                continue;
            }
            if (backtracking(s, i + 1)) {
                return true;
            }
        }
        memo[startIndex] = -1;
        return false;
    }

    /**
     * Solution: DP 代码随想录
     * Time complexity: O(n^3)
     * Space complexity: O(n)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        // dp[i] represents s.length == dp[i]
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                String str = s.substring(j, i);
                if (set.contains(str) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[n];
    }

    /**
     * 还是完全背包，字段是物品，s的长度是背包容量，字典里的每个单词都可以被重复使用，但是必须每个单词都要被装进背包
     * 因为单词顺序有要求（强调物品间的顺序），所以求的是排列数，必须要先遍历背包再遍历物品
     * Time complexity: O(n * m)
     * Space complexity: O(n)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i] represents s.subString.length == i
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (String word : wordDict) {
                if (word.length() > i){
                    continue;
                }
                // 原来的写法，没有判断在放入单词之前的背包容量，
                // 这里一定要判断之前的容量是不是正好装满，即dp[i - word.length()] == i - word.length()
                // 因为题目要求是是否可以利用字典中出现的单词拼接出 s，必须每个单词都要被装进背包，也就是说要求正好装满
                // 我这种方式的缺陷就是可以利用字典中部分拼出，但题目要求是全部，我没理清题意
                // 所以这个测试用例没有过：["dog","s","gs"] true
//                if (word.equals(s.substring(i - word.length(), i))) {
                if (dp[i - word.length()] == i - word.length() && word.equals(s.substring(i - word.length(), i))) {
                    dp[i] = dp[i - word.length()] + word.length();
                    break;
                }
            }
        }
        return dp[n] == n;
    }

    /**
     * 3 的 boolean 版
     * Time complexity: O(n * m)
     * Space complexity: O(n)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak4(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i] represents s.subString.length == i
        boolean[] dp = new boolean[n + 1];
        // 必须初始化为0，否则答案会一直为false
        dp[0] = true;
        for (int i = 1; i < n + 1; i++) {
            for (String word : wordDict) {
                if (word.length() > i){
                    continue;
                }
                if (dp[i - word.length()] && word.equals(s.substring(i - word.length(), i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
