package SkillCategory.array.DualPointers;

/**
 * @author ywxie
 * @date 2022/4/20 14:35
 * @describe
 */
public class ReSolutions {

    /**
     * 26. 删除有序数组中的重复项
     * 4.21需要再练习
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /**
     * 27.移除元素
     */
    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    /**
     * 2 83. 移动零
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 4.22 复习OK
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 注意不可以重复使用相同元素，所以left 不可以等于 right
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 344.反转字符串
     * 4.22 复习OK
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 5.最长回文串
     * 4.23 复习 NO 注意， left right 代表的是从哪里开始发散，一个是奇数，一个是偶数
     * 4.24 需要再复习
     */
    public String longestPalindrome(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = longestPalindromeHelper(s, i, i);
            String s2 = longestPalindromeHelper(s, i, i + 1);
            result = result.length() > s1.length() ? result:s1;
            result = result.length() > s2.length() ? result:s2;
        }
        return result;

    }

    private String longestPalindromeHelper(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
