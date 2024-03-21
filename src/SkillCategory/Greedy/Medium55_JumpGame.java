package SkillCategory.Greedy;

public class Medium55_JumpGame {
    /**
     * My method
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        int targetIndex = n - 1;
        int left = n - 2;
        while (left >= 0) {
            int step = nums[left];
            // needed step <= owned step
            if (targetIndex - left <= step) {
                // can reach 0 index
                if (left == 0) {
                    return true;
                }
                // update targetIndex
                targetIndex = left;
            }
            left--;
        }
        return false;
    }

    /**
     * good method
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        int targetIndex = n - 1;
        // range
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(nums[i] + i, cover);
            if (cover >= targetIndex) {
                return true;
            }

        }
        return false;
    }
}
