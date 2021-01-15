package hashtable;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ywxie
 * @date 2021/1/15 13:36
 * @describe 哈希表
 */
public class Solution {

    /**
     * 242. 有效的字母异位词
     * 如果两个单词字符串包含相同的字符及对应数量，只是字符顺序不同，则这两个单词就是有效的字母异位词
     * hashmap法，两个for循环，时间复杂度n^2
     */
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c2: t.toCharArray()){
            if (!map.containsKey(c2)){
                return false;
            }else {
                int count = map.get(c2);
                if (count > 1){
                    map.put(c2, count-1);
                }else {
                    map.remove(c2);
                }
            }
        }
        return map.isEmpty();
    }

    public boolean isAnagram2(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    public boolean isAnagram3(String s, String t){
        return Arrays.equals(s.chars().sorted().toArray(), t.chars().sorted().toArray());
    }

    public boolean isAnagram4(String s, String t){
        int[] sCounts = new int[26];
        int[] tCounts = new int[26];
        for (char ch : s.toCharArray()) {
            sCounts[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            tCounts[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sCounts[i] != tCounts[i]) {
                return false;
            }
        }
        return true;
    }
}
