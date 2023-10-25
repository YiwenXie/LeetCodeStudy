package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description 69. Sqrt(x)
 * @date 2023/10/25 21:52
 */
public class Num69Sqrt {
    /**
     * time complexity:0(log x)
     * space complexity:O(1)
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x + 1;
        while (left < right) {
            int m = left + (right - left) / 2;
            // 因为x平方根的整数部分ans是满足 k^2 ≤ x 的最大 k 值，所以寻找的是右边界
            if (m * m > x) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        // 为什么这里要减1呢？因为寻找的是右边界
        // 因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
        // 见 StudySolutions0f704.searchRightBound
        return left - 1;
    }
}
