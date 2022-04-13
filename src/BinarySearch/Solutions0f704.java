package BinarySearch;

/**
 * @author ywxie
 * @date 2022/4/13 15:29
 * @describe
 */
public class Solutions0f704 {
    /**
     * 704 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html#_704-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            //计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有效防止了 left 和 right 太大，直接相加导致溢出的情况。
            int mid = left + (right - left) / 2;
            if(target > nums[mid]){
                left = mid + 1;
            }else if(target < nums[mid]){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找，查找左边界 左闭右开
     * @param nums
     * @param target
     * @return
     */
    public int searchLeftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid;
            }else {
                // 收紧右侧边界以锁定左侧边界
                right = mid;
            }
        }
        // target 比所有数都大
        if (left >= nums.length){
            return -1;
        }
        return nums[left] == target? left: -1;
    }

    /**
     * 二分查找，查找左边界 左闭右闭 需打补丁
     * @param nums
     * @param target
     * @return
     */
    public int searchLeftBound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                // 收缩右边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    /**
     * 二分查找，查找右边界 左闭右开
     * @param nums
     * @param target
     * @return
     */
    public int searchRightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid;
            }else {
                // 收紧左侧边界以锁定右侧边界
                // 增大 left，锁定右侧边界
                // 因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
                left = mid + 1;
            }
        }
        if (left == 0){
            return -1;
        }
        return nums[left - 1] == target? (left - 1): -1;
    }

    /**
     * 二分查找，查找右边界 左闭右闭
     * @param nums
     * @param target
     * @return
     */
    public int searchRightBound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                // 收缩左边界
                // 增大 left，锁定右侧边界
                left = mid + 1;
            }
        }
        // 这里改为检查 right 越界的情况
        // 当 target 比所有元素都小时，right 会被减到 -1，所以需要在最后防止越界
        if (right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }
}
