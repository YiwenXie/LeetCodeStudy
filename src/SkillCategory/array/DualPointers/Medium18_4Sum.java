package SkillCategory.array.DualPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 9:47
 */
public class Medium18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // a = nums[i] b = nums[left] c = nums[right] d = nums[j]
        // 0 <= i < j < left < right
        // j = i + 1
        // left = j + 1
        // right = nums.length - 1
        // sorted nums -> no matter sort but can use sort to find wrong answer and right answer
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // a > target and a >= 0 means b and c and d all >= target, so sum can meet equals target
            if (nums[i] > target && nums[i] >= 0) {
                break;
            }
            // solving the repeat a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int d = nums[j];
                if (a + d > target && nums[i] >= 0) {
                    return result;
                }
                // solving the repeat d
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int b = nums[left];
                    int c = nums[right];
                    int sum = a + b + c + d;
                    // if sum > target -> means right is too big -> right--
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        // if sum < target -> means left is too small -> left++
                        left++;
                    } else {
                        // add answer
                        result.add(Arrays.asList(a, d, b, c));
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

        }
        return result;
    }
}
