package hashtable;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 349. 两个数组的交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).filter(set::contains).distinct().toArray();
        //或者两个for循环后
//        Integer[] nums3 = set.toArray(new Integer[set.size()]);
//        return Arrays.stream(nums3).mapToInt(Integer::intValue).toArray();
    }

    /**
     * 202. 快乐数
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * 参考英文网站热评第一。这题可以用快慢指针的思想去做，有点类似于检测是否为环形链表那道题
     * //如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是
     * //两者一定会相等。如果没有循环重复，那么最后快慢指针也会相等，且都等于1。
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = toAddSquareNum(slow);
            fast = toAddSquareNum(fast);
            fast = toAddSquareNum(fast);
            if (fast == 1){
                return true;
            }
        }while (slow != fast);
        return false;
    }

    private int toAddSquareNum(int m){
        int sum = 0;
        while (m != 0) {
            sum += (m%10) * (m%10);
            m = m/10;
        }
        return sum;
    }

    /**
     * 1. 两数之和
     * 两层for循环，时间复杂度n^2
     */
    public int[] getSum(int[] nums, int target){
        int length = nums.length;
        int index[] = new int[2];
        for (int i = 0;i < length;i++){
            for (int j = i + 1; j < length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
        }
        return index;
    }

    public int[] getSum2(int[] nums, int target){
        int length = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] nums1 = new int[2];
        for (int i = 0; i < length; i++){
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i){
                nums1[0] = i;
                nums1[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return nums1;
    }
}
