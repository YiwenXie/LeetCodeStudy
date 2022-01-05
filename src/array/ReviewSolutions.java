package array;

import java.util.Arrays;

/**
 * @author ywxie
 * @date 2022/1/5 21:42
 * @describe
 */
public class ReviewSolutions {

    /**
     * 704 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html#_704-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 27 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if (nums[fastIndex] != val){
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
//        //1.将负数变正数
//        for (int i = 0; i < nums.length; i++){
//            if (nums[i] < 0){
//                nums[i] = Math.abs(nums[i]);
//            }else {
//                break;
//            }
//        }
//        //2.将数据原地排序
//        Arrays.sort(nums);
//        //3.遍历变成平方
//        for (int i = 0; i < nums.length; i++){
//            nums[i] = nums[i] * nums[i];
//        }
        //第二种
//        for (int i = 0; i < nums.length; i++){
//            nums[i] *= nums[i];
//        }
//        Arrays.sort(nums);
        //第三种 双指针法
        int[] result = new int[nums.length];
        int index = nums.length - 1;
        int j = nums.length - 1;
        int i = 0;
        while (i <= j){
            if (nums[i] * nums[i] >= nums[j] * nums[j]){
                result[index--] = nums[i] * nums[i];
                i++;
            }else if (nums[i] * nums[i] < nums[j] * nums[j]){
                result[index--] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }


    public static void main(String[] args) {

    }
}
