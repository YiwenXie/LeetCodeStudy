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

    /**
     * 27. 移除元素
     * 双指针法：快慢指针：「通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。」
     */
    public int removeElement(int[] nums, int val){
        int length = nums.length;
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < length; fastIndex++){
            if (nums[slowIndex] != val) {
                nums[slowIndex] = nums[fastIndex + 1];
            }else {
                length--;
            }
        }
        return length;
    }

    /**
     * 209. 长度最小的子数组
     * 滑动窗口
     * 在本题中实现滑动窗口，主要确定如下三点：
     *
     *     窗口内是什么？
     *     如何移动窗口的起始位置？
     *     如何移动窗口的结束位置？
     *
     * 窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
     *
     * 窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
     *
     * 窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，窗口的起始位置设置为数组的起始位置就可以了。
     */
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        int i = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int j = 0; j < length; j++){
            sum += nums[j];
            while (sum >= s){
                int subLength = j - i + 1;
                minLength = Math.min(minLength, subLength);
                sum -= nums[i++];
            }
        }
        return minLength < Integer.MAX_VALUE ? minLength: 0;
    }

    /**
     * 59. 螺旋矩阵 II
     */
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int count = 1;
        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        while (count <= n * n){
            for (int i = left; i <= right; i++){
                arr[up][i] = count++;
            }
            up++;
            for (int i = up; i <= down; i++){
                arr[i][right] = count++;
            }
            right--;
            for (int i = right; i >= left; i--){
                arr[down][i] = count++;
            }
            down--;
            for (int i = down; i >= up; i--){
                arr[i][left] = count++;
            }
            left++;
        }
        return arr;
    }

}
