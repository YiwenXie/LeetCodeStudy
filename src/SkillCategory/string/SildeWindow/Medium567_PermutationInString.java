package SkillCategory.string.SildeWindow;

import java.util.HashMap;

public class Medium567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int value = 0;
        while (right < s2.length()) {
            char r = s2.charAt(right++);
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) {
                    value++;
                }
            }
            while (right - left >= s1.length()) {
                if (value == need.size()) {
                    return true;
                }
                char l = s2.charAt(left++);
                if (need.containsKey(l)) {
                    if (window.get(l).equals(need.get(l))) {
                        value--;
                    }
                    window.put(l, window.get(l) - 1);
                }
            }
        }
        return false;
    }
}
