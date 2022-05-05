package SkillCategory.array.preIndexSum;

import java.util.HashMap;

/**
 * @author ywxie
 * @date 2022/4/16 16:20
 * @describe
 */
public class Solution {
    /**
     * 303. 区域和检索 - 数组不可变
     */
    class NumArray {

        private int[] presSum;

        public NumArray(int[] nums) {
            presSum = new int[nums.length + 1];
            for (int i = 1; i < presSum.length; i++) {
                presSum[i] = presSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return presSum[right + 1] - presSum[left];
        }
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     */
    class NumMatrix {

        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            preSum = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j -1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];

        }
    }

    /**
     * 560. 和为 K 的子数组
     * 【暴力解法】 ok - 100
     * 暴力解法思路：和为K的子数组，即前缀和数组相减的数为K，即303区域和搜索题目的变形题，有多少这样的区域和
     * 优化的思路是：我直接记录下有几个 preSum[j] 和 preSum[i] - k 相等，直接更新结果，就避免了内层的 for 循环。
     *              我们可以用哈希表，在记录前缀和的同时记录该前缀和出现的次数。
     */
    public int subarraySum(int[] nums, int k) {

        // 暴力解法
//        return violenceSubarraySum(nums, k);
        // 优化解法
        return optimizeSubarraySum(nums, k);
    }

    private int violenceSubarraySum(int[] nums, int k){
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int result = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[j] == preSum[i] - k){
                    result++;
                }
            }
        }
        return result;
    }

    private int optimizeSubarraySum(int[] nums, int k){
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // base case
        hashMap.put(0 , 1);
        int result = 0,sum0_i = 0;
        for (int i = 0; i < nums.length; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (hashMap.containsKey(sum0_j)) {
                result += hashMap.get(sum0_j);
            }
            // 把前缀和 nums[0..i] 加入并记录出现次数
            hashMap.put(sum0_i, hashMap.getOrDefault(sum0_i, 0) + 1);
        }
        return result;
    }
}
