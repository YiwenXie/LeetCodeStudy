package SkillCategory.array.DualPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 9:32
 */
public class Medium15_3Sum {
    /**
     * Solution: dual pointer
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // a = nums[i] b = nums[left] c = nums[right]
        // 0 <= i < left < right
        // left = i + 1
        // right = nums.length - 1
        // sorted nums -> no matter sort but can use sort to find wrong answer and right answer
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // a > 0 means b and c all > 0, so sum can meet equals 0
            if (nums[i] > 0) {
                break;
            }
            // solving the repeat a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int a = nums[i];
                int b = nums[left];
                int c = nums[right];
                int sum = a + b + c;
                // if a + b + c > 0 -> means right is too big -> right--
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    // if a + b + c < 0 -> means left is too small -> left++
                    left++;
                } else {
                    // add answer
                    result.add(Arrays.asList(a, b, c));
                    // solving the repeat b and c
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // left and right shrink at sametime
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
