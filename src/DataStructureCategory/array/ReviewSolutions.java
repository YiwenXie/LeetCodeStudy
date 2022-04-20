package DataStructureCategory.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ywxie
 * @date 2022/1/5 21:42
 * @describe
 */
public class ReviewSolutions {

    /**
     * 27 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 双指针
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
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
        while (i <= j) {
            if (nums[i] * nums[i] >= nums[j] * nums[j]) {
                result[index--] = nums[i] * nums[i];
                i++;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[index--] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int slowIndex = 0;
        int result = Integer.MAX_VALUE;
        int sum = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            sum += nums[fastIndex];
            while (sum >= s) {
                int temp = fastIndex - slowIndex + 1;
                result = Math.min(temp, result);
                sum -= nums[slowIndex++];
            }
        }
        return result == Integer.MAX_VALUE ? 0: result;
    }

    /**
     * 练习题系列
     */
    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return right + 1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int resultL = findLeftBorder(nums, target);
        int resultR = findRightBorder(nums, target);
        if (resultL == -2 || resultR == -2) {
            return new int[]{-1, -1};
        }
        if (resultR - resultL > 1) {
            return new int[]{resultL + 1, resultR - 1};
        }
        return new int[]{-1, -1};
    }

    private static int findRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int resultR = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
                resultR = left;
            }
        }
        return resultR;
    }

    private static int findLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int resultL = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
                resultL = right;
            }
        }
        return resultL;
    }

    /**
     * 904. 水果成篮
     * @param fruits
     * @return
     */
//    public int totalFruit(int[] fruits) {
//
//    }

    public int[] MySort (int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public int[] MySort2(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int key = array[start];//用待排数组的第一个作为中枢
            int i = start;
            for (int j = start + 1; j <= end; j++) {
                if (key > array[j]) {
                    swap(array, j, ++i);
                }
            }
            array[start] = array[i];//先挪，然后再把中枢放到指定位置
            array[i] = key;
            quickSort(array, start, i - 1);
            quickSort(array, i + 1, end);
        }
    }

    //交换两个数的值
    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (k <= 0 || input == null){
            return list;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++){
            list.add(input[i]);
        }
        return list;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>(k);
        //根据题意要求，如果K>数组的长度，返回一个空的数组
        if (k > input.length || k == 0) {
            return res;
        }
        //创建最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1, num2) -> num2 - num1);
        //先在堆中放数组的前k个元素
        for (int i = 0; i < k; ++i) {
            queue.offer(input[i]);
        }
        //因为是最大堆，也就是堆顶的元素是堆中最大的，遍历数组后面元素的时候，
        //如果当前元素比堆顶元素大，就把堆顶元素给移除，然后再把当前元素放到堆中，
        for (int i = k; i < input.length; ++i) {
            if (queue.peek() > input[i]) {
                queue.poll();
                queue.offer(input[i]);
            }
        }
        //最后再把堆中元素转化为数组
        for (int i = 0; i < k; ++i) {
            res.add(queue.poll());
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
