package SkillCategory.Greedy;

public class Medium376_WiggleSubsequence {
    public static void main(String[] args) {
        Medium376_WiggleSubsequence medium376 = new Medium376_WiggleSubsequence();
        int[] nums = new int[]{3, 3, 3, 2, 5};
        System.out.println(medium376.wiggleMaxLength(nums));
    }

    public int wiggleMaxLength(int[] nums) {
        int length = 1;
        boolean isFirst = true;
        boolean isPositive = false;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == 0) {
                continue;
            }
            if (isFirst) {
                length++;
                isPositive = diff > 0;
                isFirst = false;
                continue;
            }
            if (diff > 0 && !isPositive) {
                isPositive = true;
                length++;
            } else if (diff < 0 && isPositive) {
                isPositive = false;
                length++;
            }
        }
        return length;
    }
}
