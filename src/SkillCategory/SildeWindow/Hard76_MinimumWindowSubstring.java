package SkillCategory.SildeWindow;

import java.util.HashMap;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 13:37
 */
public class Hard76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (Character c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int value = 0;
        String result = "";
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    value++;
                }
            }
            while (value == need.size()) {
                if (len > right - left) {
                    len = right - left;
                    result = s.substring(left, right);
                }
                char l = s.charAt(left++);
                if (need.containsKey(l)) {
                    window.put(l, window.get(l) - 1);
                    if (!need.get(l).equals(window.get(l))) {
                        value--;
                    }
                }
            }
        }
        return result;
    }
}
