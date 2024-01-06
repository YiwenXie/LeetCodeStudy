package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/28 21:20
 */
public class Easy35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
