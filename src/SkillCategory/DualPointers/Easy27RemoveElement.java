package SkillCategory.DualPointers;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/10 14:47
 */
public class Easy27RemoveElement {
    /**
     * Solution: Dual Pointers
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
