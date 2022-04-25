package SkillCategory.array.SlidingWindow;

import java.util.*;

/**
 * @author ywxie
 * @date 2022/4/22 15:48
 * @describe 滑动窗口-学习类
 */
public class StudySolutions {
    /**
     * 76. 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> window  = new HashMap<>();
        HashMap<Character, Integer> need  = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0,right = 0;
        int valid = 0;
        int start = 0;
        int length = Integer.MAX_VALUE;
        while (right < s.length()){
            // c是即将被判断是否移入窗口的字符
            char c = s.charAt(right++);
            // 判断 c 是否应该加入窗口 / 判断是否增大右侧窗口
            if (need.containsKey(c)){
                // 把 c 加入窗口
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 判断 c 是否符合条件
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == need.size()){
                // 更新最小覆盖字符串
               if (right - left < length){
                   start = left;
                   length = right - left;
               }
                // d 是即将移出窗口的字符
                char d = s.charAt(left++);
                if (need.containsKey(d)){
                    if (need.get(d).equals(window.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        // 返回结果
        return length == Integer.MIN_VALUE ? "":s.substring(start, start + length);
    }

    /**
     * 567. 字符串的排列
     */
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int left = 0,right = 0;
        int valid = 0;
        while (right < s2.length()){
            char c = s2.charAt(right++);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 重点：判断左侧窗口应该开始收缩的条件
            while (right - left >= s1.length()){
                if (valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left++);
                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        int left = 0,right = 0;
        int valid = 0;
        List<Integer> result = new ArrayList<>();
        while (right < s.length()){
            char c = s.charAt(right++);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 重点：判断左侧窗口应该开始收缩的条件
            while (right - left >= p.length()){
                if (valid == need.size()){
                    result.add(left);
                }
                char d = s.charAt(left++);
                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return result;
    }

    /**
     * 3. 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
//        Set<Character> set = new HashSet<>();
        HashMap<Character, Integer> window = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            set.add(s.charAt(i));
//        }
        int left = 0, right = 0;
        int valid = 0;
        int len = 0;
        while (right < s.length()){
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1){
                char d = s.charAt(left++);
                window.put(d, window.get(d) - 1);
            }
            len = Math.max(len, right - left);
        }
        return len;
    }
}
