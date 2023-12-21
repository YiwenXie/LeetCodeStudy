package SkillCategory.hashtable;

import java.util.HashMap;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/21 19:00
 */
public class Easy383RansomNote {
    /**
     * Solution: hashtable
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : ransomNote.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int right = 0;
        int value = 0;
        while (right < magazine.length()) {
            char c = magazine.toCharArray()[right++];
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    value++;
                }
            }
            if (value == need.size()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Solution: array
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] hash = new int[26];
        for (char c : magazine.toCharArray()) {
            hash[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            hash[c - 'a']--;
        }
        for (int count : hash
        ) {
            if (count < 0) {
                return false;
            }
        }
        return true;
    }
}
