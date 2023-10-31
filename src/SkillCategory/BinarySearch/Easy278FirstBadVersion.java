package SkillCategory.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/25 22:06
 */
public class Easy278FirstBadVersion {

    /**
     * 1 <= bad <= n <= 231 - 1
     * time complexity: O(log n)
     * space complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int firstBadVersion(int n) {
        int left = 1;
        // 注意right的初始边界，为什么这里right的初始边界 是 n 而不是 n + 1，因为n就是n的长度，不要被数组的长度惯性+1
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean isBadVersion(int n) {
        return true;
    }
}
