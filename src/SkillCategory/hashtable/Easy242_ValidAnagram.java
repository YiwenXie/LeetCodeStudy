package SkillCategory.hashtable;

import java.util.HashMap;

public class Easy242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : s.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int value = 0;
        for (char r : t.toCharArray()) {
            if (!need.containsKey(r)) {
                return false;
            }
            window.put(r, window.getOrDefault(r, 0) + 1);
            if (window.get(r).equals(need.get(r))) {
                value++;
            }
        }
        return value == need.size();
    }

    public boolean isAnagram2(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
        }
        for (int i : map) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
