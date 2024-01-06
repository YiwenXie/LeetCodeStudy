package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/24 22:08
 */
public class Medium2560_HouseRobberIV {

    /**
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 109
     * 1 <= k <= (nums.length + 1)/2
     *
     * @param nums
     * @param k
     * @return
     */
    public int minCapability(int[] nums, int k) {
        // 1 <= nums[i] <= 109
        int left = 1;
        int right = 0;
        // find right bound = find maximum capability = maximum house's money
        for (int num : nums
        ) {
            right = Math.max(num, right);
        }
        // right = right + 1
        right++;
        int i = 0;
        // record numbers of robbed house
        int count = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
            // 有一个最小的mid使得rob(nums, mid, i, count) >= k成立
            if (f(nums, mid, i, count) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * @param nums
     * @param m     minimum steal capability
     * @param i     index
     * @param count numbers of robbed house
     * @return count
     */
    public int f(int[] nums, int m, int i, int count) {
        if (i < nums.length) {
            // capability >= house money, can steal, so count robbed house
            if (nums[i] <= m) {
                count++;
                // i+2, because can't rob adjacent house
                return f(nums, m, i + 2, count);
            } else {
                // capability < house money, can't steal, so start rob next house
                return f(nums, m, i + 1, count);
            }
        }
        return count;
    }
}
