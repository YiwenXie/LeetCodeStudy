package DataStructureCategory.hashtable;

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

    /**
     * 454. 四数相加 II
     * 计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
     * 暴力法，超出时间限制
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B.length; j++){
                for (int k = 0; k < C.length; k++){
                    for (int l = 0; l < D.length; l++) {
                        if (A[i] + B[j] + C[k] + D[l] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 454. 四数相加 II
     * 遍历 A 和 B 所有元素和的组合情况，并记录在 ab_map 中，ab_map 的 key 为两数和，value 为该两数和出现的次数
     * 遍历 C 和 D 所有元素和的组合情况，取和的负值判断其是否在 ab_map 中，若存在则取出 ab_map 对应的 value 值，count = count + value
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        HashMap<Integer, Integer> abMap = new HashMap<>();
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B.length; j++){
                abMap.put(A[i] + B[j], abMap.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }
        for (int k = 0; k < C.length; k++){
            for (int l = 0; l < D.length; l++) {
                if (abMap.containsKey(0 - C[k] - D[l])){
                    count = count + abMap.get(0 - C[k] - D[l]);
                }
            }
        }
        return count;
    }

    /**
     * 383. 赎金信
     * 同242
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char r : ransomNote.toCharArray()){
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        for (char m : magazine.toCharArray()){
            if (map.containsKey(m)){
                if (map.get(m) <= 1){
                    map.remove(m);
                }else {
                    map.put(m, map.get(m) - 1);
                }
            }
        }
        if (map.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 383. 赎金信
     * 同242
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        StringBuilder stringBuilder = new StringBuilder(magazine);
        int index;
        for (char c : ransomNote.toCharArray()) {
            index = stringBuilder.indexOf(String.valueOf(c));
            if (index >= 0) {
                stringBuilder.deleteCharAt(index);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 15. 三数之和
     * 暴力法超时
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set result = new HashSet();
        for(int i =0; i < nums.length; i++) {
            for (int j = i +1; j< nums.length; j++) {
                for(int l = j +1; l < nums.length; l++) {
                    if (nums[i] + nums[j]+nums[l] == 0) {
                        List result2 = new ArrayList();
                        result2.add(nums[i]);
                        result2.add(nums[j]);
                        result2.add(nums[l]);
                        Collections.sort(result2);
                        result.add(result2);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 15. 三数之和
     * 指针法
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;   // 跳过重复值
                        }
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    /**
     * 18. 四数之和
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();
        if (nums.length == 4){
            if (nums[0] + nums[1] + nums[2] + nums[3] == 0){
                ls.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
                return ls;
            }
        }else if (nums.length < 4){
            return ls;
        }
        for (int i = 0; i < nums.length - 3; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2 ; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int l = j+1; int r = nums.length - 1; int sum = target - nums[i] - nums[j];

                while (l < r){
                    if (nums[l] + nums[r] == sum){
                        ls.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l+1]){
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;   // 跳过重复值
                        }
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }
        return ls;
    }

}
