package BinarySearch;

import java.util.HashMap;

/**
 * @author ywxie
 * @date 2022/4/18 12:46
 * @describe
 */
public class ReSolutions {
    /**
     * 704. 二分查找
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > target){
                right = mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置
     * 思路：寻找左边界
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > target){
                right = mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 852. 山脉数组的峰顶索引
     * 思路：左单调增右单调减的索引位置，即左右两边都比自己小的位置，即数组最大值
     */
    public int peakIndexInMountainArray(int[] arr) {
        // 暴力解法 O（N）
//        return violencePeakIndexInMountainArray(arr);
        // 优化解法 O(log n)
        return optimizePeakIndexInMountainArray(arr);
    }

    private int violencePeakIndexInMountainArray(int[] arr){
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                index = i;
                max = arr[i];
            }
        }
        return index;
    }

    private int optimizePeakIndexInMountainArray(int[] arr){
        int left = 0;
        int right = arr.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            int pre = mid - 1;
            int post = mid + 1;
            if (pre < left || post >= right){
                break;
            }
            // 返回条件
            if (arr[pre] < arr[mid] && arr[mid] > arr[post]){
                return mid;
            }else {
                if (arr[mid] < arr[post]){
                    left = mid + 1;
                }else if (arr[mid] < arr[pre]){
                    right = mid;
                }
            }
        }
        return 0;
    }

    /**
     * 367. 有效的完全平方数
     * 构造半分数组
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 1){
            return true;
        }
        int left = 1;
        int right = num / 2;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (mid * mid > num){
                right = mid - 1;
            }else if (mid * mid < num){
                left = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 1385. 两个数组间的距离值
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        return 0;
    }
}
