package SkillCategory.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Medium128_LongestConsecutiveSequence {
    /**
     * Sort
     * Time complexity: O(n * logn + n) 不符合题意
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int length = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] - nums[i - 1] != 1) {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }
                length = 1;
            } else {
                length++;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int currentLength = 1;
                int currentNum = num;
                while (set.contains(currentNum + 1)) {
                    currentLength++;
                    currentNum++;
                }
                maxLength = Math.max(currentLength, maxLength);
            }
        }
        return maxLength;
    }
}
