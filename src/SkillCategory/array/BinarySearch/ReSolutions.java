package SkillCategory.array.BinarySearch;

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
     * 875. 爱吃香蕉的珂珂
     * OK
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000 + 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (minEatingSpeedF(piles, mid) > h){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    private int minEatingSpeedF(int[] piles, int x){
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0){
                hours++;
            }
        }
        return hours;
    }

    /**
     *  1011. 在 D 天内送达包裹的能力
     *  4.23号再复习一遍
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 1;
        for (int weight: weights) {
            left = Math.max(weight, left);
            right += weight;
        }
        while (left < right){
            int mid = left + (right - left) / 2;
            if (shipWithinDaysF(weights, mid) > days){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    private int shipWithinDaysF(int[] weights, int x){
        int days = 0;
        for (int i = 0; i < weights.length;) {
            // 重点是这里！
            int cap = x;
            while (i < weights.length){
                if (cap >= weights[i]){
                    cap -= weights[i];
                }else {
                    break;
                }
                i++;
            }
            days++;
        }
        return days;
    }
}
