package SkillCategory.string.SildeWindow;

import java.util.HashMap;

public class Medium3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int len = Integer.MIN_VALUE;
        while (right < s.length()) {
            char r = s.charAt(right++);
            window.put(r, window.getOrDefault(r, 0) + 1);
            while (window.get(r) > 1) {
                char l = s.charAt(left++);
                window.put(l, window.get(l) - 1);
            }
            len = Math.max(len, right - left);
        }
        return len == Integer.MIN_VALUE ? 0 : len;
    }
}
