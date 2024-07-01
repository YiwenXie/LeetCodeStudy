package SkillCategory.array.other;

import java.util.Arrays;

public class Medium136_SingleNumber {
    public static void main(String[] args) {
        Medium136_SingleNumber medium136 = new Medium136_SingleNumber();
//        int[] nums = new int[]{2, 2, 1};
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(medium136.singleNumber(nums));
    }
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = nums[i];
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }

        }
        return result;
    }
}
