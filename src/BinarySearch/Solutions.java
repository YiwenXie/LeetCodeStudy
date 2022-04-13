package BinarySearch;

/**
 * @author ywxie
 * @date 2022/4/13 14:24
 * @describe
 */
public class Solutions {
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
     * 学了704之后的练习 ok-100过
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 思路：寻找左边界与右边界
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = leftBoundOfSearchRange(nums, target);
        result[1] = rightBoundOfSearchRange(nums, target);
        return result;
    }

    public int leftBoundOfSearchRange(int[] nums, int target) {
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
        // 检查left越界
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    public int rightBoundOfSearchRange(int[] nums, int target) {
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
                left = mid + 1;
            }
        }
        // 检查right越界
        if (right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }
}
