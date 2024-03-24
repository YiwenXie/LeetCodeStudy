package SkillCategory.Greedy;

import java.util.Arrays;

public class Easy1005_MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        Easy1005_MaximizeSumOfArrayAfterKNegations easy1005 = new Easy1005_MaximizeSumOfArrayAfterKNegations();
//        int[] nums = new int[]{5, 6, 9, -3, 3};
//        int k = 2;
        int[] nums = new int[]{-8, 3, -5, -3, -5, -2};
        int k = 6;
        System.out.println(easy1005.largestSumAfterKNegations(nums, k));
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            } else {
                break;
            }
        }
        Arrays.sort(nums);
        while (k > 0) {
            nums[0] = -nums[0];
            k--;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        return k % 2 == 0 ? sum : sum - 2 * nums[0];
    }
}
