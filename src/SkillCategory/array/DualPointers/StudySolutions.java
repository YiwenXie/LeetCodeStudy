package SkillCategory.array.DualPointers;

import DataStructureCategory.listnode.ListNode;

/**
 * @author ywxie
 * @date 2022/4/19 16:31
 * @describe 双指针学习
 * 双指针技巧主要分为两类：左右指针和快慢指针。
 * 左右指针：两个指针相向而行或者相背而行
 * 快慢指针：两个指针同向而行，一快一慢。
 */
public class StudySolutions {


    /**
     * 26. 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                // 我们这里是先给 slow++ 然后再赋值给nums[slow] ，
                // 保证了 nums[0..slow] 都是无重复的元素
                // 当 fast 指针遍历完整个数组 nums 后，nums[0..slow] 就是整个数组去重之后的结果。
                nums[++slow] = nums[fast];
            }
        }
        // 注意：因为返回的是数组长度，所以是最后的索引+1
        return slow + 1;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 学完链表再左
     */
//    ListNode deleteDuplicates(ListNode head) {
//
//    }

    /**
     * 27.移动元素
     */
    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                // 注意这里和有序数组去重的解法有一个细节差异，
                // 我们这里是先给 nums[slow] 赋值然后再给 slow++，
                // 这样可以保证 nums[0..slow-1] 是不包含值为 val 的元素的，
                nums[slow++] = nums[fast];
            }
        }
        // 最后的结果数组长度就是 slow。
        return slow;
    }

    /**
     * 283. 移动零
     * 0k -100
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow++] = nums[fast];
                nums[fast] = temp;
            }
        }
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 344.反转字符串
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 回文串判断 OK
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right){
            if (chars[left] != chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 5.最长回文串
     */
    public String longestPalindrome(String s){
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }



}
