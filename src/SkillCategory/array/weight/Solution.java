package SkillCategory.array.others;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author ywxie
 * @date 2022/4/30 14:44
 * @describe
 */
public class Solution {


    /**
     * 528. 按权重随机选择
     * 1、根据权重数组 w 生成前缀和数组 preSum。
     * 2、生成一个取值在 preSum 之内的随机数，用二分搜索算法寻找大于等于这个随机数的最小元素索引。
     * 3、最后对这个索引减一（因为前缀和数组有一位索引偏移），就可以作为权重数组的索引，即最终答案:
     */


        private int[] preSum;
        private Random random = new Random();

        public Solution(int[] w) {
            preSum = new int[w.length + 1];
            preSum[0] = 0;
            // 构建前缀和数组，偏移一位留给 preSum[0]
            for (int i = 1; i < w.length + 1; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }
        }

        public int pickIndex() {
            int n = preSum.length;
            // 在闭区间 [1, preSum[n - 1]] 中随机选择一个数字
            int target = random.nextInt(preSum[n - 1]) + 1;
            // 获取 target 在前缀和数组 preSum 中的索引
            // 别忘了前缀和数组 preSum 和原始数组 w 有一位索引偏移
            return searchLeftBound(preSum, target) - 1;
        }

        private int searchLeftBound(int[] nums, int target){
            int left = 0;
            int right = nums.length;
            while (left < right){
                int mid = left + (right - left) / 2;
                if (nums[mid] < target){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
            return left;
        }

    /**
     * 870. 优势洗牌
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 创建一个存放int[] 数组的优先级队列，以索引1（num2[索引0]的值）的大小进行降序排序，索引0为num2的下标
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (int[] array1, int[] array2) -> array2[1] - array1[1]
        );
        // 将num2存入优先级队列里
        for (int i = 0; i < nums2.length; i++) {
            queue.offer(new int[]{i, nums2[i]});
        }
        // 对 num1 进行升序排序
        Arrays.sort(nums1);
        // 建立一个返回数组
        int[] res = new int[nums1.length];
        int left = 0, right = nums1.length - 1;
        // 开始进行比较，只要队列里的num2还没比较完
        while (!queue.isEmpty()){
            // 从队列中取出目前num2里最大的值
            int[] maxNum2 = queue.poll();
            int maxNum2Value = maxNum2[1], index = maxNum2[0];
            // 开始判断num1中哪个会比较有优势
            // 用num1中最大的数和当前num2中最大的数比
            if (nums1[right] > maxNum2Value){
                // num1 比 num2 大
                res[index] = nums1[right--];
            }else {
                // num1 比 num2 小，用num1的最小值消耗掉num2的最大值
                res[index] = nums1[left++];
            }
        }
        return res;
    }
}
