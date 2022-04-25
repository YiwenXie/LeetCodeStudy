package SkillCategory.array.SlidingWindow;

import java.util.HashMap;

/**
 * @author ywxie
 * @date 2022/4/25 10:33
 * @describe 滑动窗口复习
 */
public class ReSolutions {
    /**
     * 76. 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()){
            char c = s.charAt(right++);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            while (valid == need.size()){
                if (right - left < len){
                    len = right - left;
                    start = left;
                }
                char d = s.charAt(left++);
                if (need.containsKey(d)){
                    if (need.get(d).equals(window.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "":s.substring(start, start + len);
    }

    /**
     * 567. 字符串的排列
     */
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s2.length()){
            char c = s2.charAt(right++);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            while (right - left >= s1.length()){
                if (valid == need.size()){
                    return  true;
                }
                char d = s2.charAt(left++);
                if (need.containsKey(d)){
                    if (need.get(d).equals(window.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}
