package SkillCategory.hashtable;

import java.util.ArrayList;
import java.util.List;

public class Easy1002_FindCommonCharacters {
    /**
     * Solution: Array Hash
     * Time complexity: O(m * n)
     * Space complexity: O(1)
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {
        int[] hash = new int[26];
        for (char c : words[0].toCharArray()) {
            hash[c - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            int[] otherHash = new int[26];
            for (char c : word.toCharArray()) {
                otherHash[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                hash[j] = Math.min(hash[j], otherHash[j]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            while (hash[j] != 0) {
                char c = (char) (j + 'a');
                result.add(String.valueOf(c));
                hash[j] = hash[j] - 1;
            }
        }
        return result;
    }
}
