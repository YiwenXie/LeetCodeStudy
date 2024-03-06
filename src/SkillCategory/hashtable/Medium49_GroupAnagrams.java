package SkillCategory.hashtable;

import java.util.*;

public class Medium49_GroupAnagrams {
    /**
     * Brute Force
     * Time Complexity: O(n + n^2 * 26)
     * Space Complexity: O(n + n * 26)
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, int[]> map = new HashMap<>();
        for (String str : strs) {
            int[] dict = new int[26];
            for (char c : str.toCharArray()) {
                dict[c - 'a']++;
            }
            map.put(str, dict);
        }

        boolean[] use = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (use[i]) {
                continue;
            }
            String str = strs[i];
            List<String> list = new ArrayList<>();
            list.add(str);
            use[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                if (use[j]) {
                    continue;
                }
                String str2 = strs[j];
                if (str2.equals(str)) {
                    use[j] = true;
                    list.add(str2);
                    continue;
                }
                boolean flag = isAnagram(str, str2, map);
                if (flag) {
                    use[j] = true;
                    list.add(str2);
                }
            }
            result.add(list);
        }
        return result;
    }

    private boolean isAnagram(String s, String t, Map<String, int[]> map) {
        int[] sArray = map.get(s);
        int[] tArray = map.get(t);
        for (int i = 0; i < 26; i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sort + HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(n + n * 26)
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String string : strs) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sortedString = Arrays.toString(chars);
            map.putIfAbsent(sortedString, new ArrayList<>());
            map.get(sortedString).add(string);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
