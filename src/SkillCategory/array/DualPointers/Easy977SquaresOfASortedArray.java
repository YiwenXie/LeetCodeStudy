package SkillCategory.array.DualPointers;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/10 14:24
 */
public class Easy977SquaresOfASortedArray {
    /**
     * Solution: Brute force
     * Time complexity: O(n + logn)
     * Space complexity: O(n);
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * Solution: Dual pointers
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int p = nums.length - 1;
        while (left <= right) {
            int a = nums[left] * nums[left];
            int b = nums[right] * nums[right];
            if (a >= b) {
                result[p] = a;
                p--;
                left++;
            } else {
                result[p] = b;
                p--;
                right--;
            }
        }
        return result;
    }
}
