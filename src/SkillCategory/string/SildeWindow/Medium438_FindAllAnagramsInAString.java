package SkillCategory.string.SildeWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Medium438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int value = 0;
        while (right < s.length()) {
            char r = s.charAt(right++);
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) {
                    value++;
                }
            }
            while (right - left >= p.length()) {
                if (value == need.size()) {
                    result.add(left);
                }
                char l = s.charAt(left++);
                if (need.containsKey(l)) {
                    if (window.get(l).equals(need.get(l))) {
                        value--;
                    }
                    window.put(l, window.get(l) - 1);
                }
            }
        }
        return result;
    }
}
