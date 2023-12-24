package SkillCategory.string.DualPointer;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 10:37
 */
public class Medium151_ReverseWordsInAString {
    /**
     * Solution: Dual Pointer
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].isEmpty()) {
                continue;
            }
            sb.append(strings[i]).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Solution: Dual Pointer
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        s = s.trim();
        int left = s.length() - 1;
        int right = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (left >= 0) {
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            sb.append(s, left + 1, right + 1).append(" ");
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            right = left;
        }
        return sb.toString().trim();
    }
}
