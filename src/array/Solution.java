package array;

/**
 * @author ywxie
 * @date 2021/1/12 14:21
 * @describe
 */
public class Solution {

    /**
     * 35. 搜索插入位置
     * 暴力法
     * 最大时间复杂度O(n)，空间O（1）
     */
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int index = length;
        for (int i = 0; i < length; i++){
            if (nums[i] >= target){
                return i;
            }
        }
        return index;
    }

    /**
     * 35. 搜索插入位置
     * 二分法
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int searchInsertByDivideTwo(int[] nums, int target){
        int length = nums.length;
        int l = 0;
        int r = length - 1;
        while (l <= r){
            int mid = l + ((r - l) / 2);  // 防止溢出 等同于(left + right)/2
            if (nums[mid] < target){
                l = mid + 1;    // target 在右区间，所以[middle + 1, right]
            }else if (nums[mid] > target){
                r = mid - 1;    // target 在左区间，所以[left, middle - 1]
            }else {
                return mid;
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前  [0, -1]
        // 目标值等于数组中某一个元素  return middle;
        // 目标值插入数组中的位置 [left, right]，return  right + 1
        // 目标值在数组所有元素之后的情况 [left, right]， return right + 1
        return r + 1;
    }
}
