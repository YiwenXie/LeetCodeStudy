package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description 单调递增或递减关系，寻找最大最小值，毫不犹豫用二分
 * @date 2023/10/25 20:01
 */
public class BinarySearchTemplate {

    public boolean f(int m) {
        return true;
    }

    public boolean g(int m) {
        return true;
    }

    /**
     * 二分查找模板
     *
     * @param left
     * @param right
     * @return time complexity:O(log(r-l)) * O(f(m) + g(m))
     * space complexity:O(1)
     */
    public int binarySearchTemplate(int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(mid)) {
                return mid;
            } else if (g(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 找到了最小的left使得g(mid)==true
        return left; // or not found
    }

    public int f(int[] nums, int m) {
        return nums[m];
    }

    public int findTarget(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(nums, mid) < target) {
                left = mid + 1;
            } else if (f(nums, mid) > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return left == nums.length ? -1 : left;
    }

    /**
     * first index of i, such that A[i] >= x
     * 寻找x的左边界
     * 也就是值为x的最左的下标
     */
    public int lowerBound(int[] nums, int target, int left, int right) {
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

    /**
     * first index of i, such that A[i] > x
     * 也就是在x左边的数有多少个
     * 也就是比x大的最小的数的下标
     */
    public int upperBound(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findLeftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // g(m)
            if (f(nums, mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // target 比所有数都大
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int findRightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // g(m)
            if (f(nums, mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }
}
