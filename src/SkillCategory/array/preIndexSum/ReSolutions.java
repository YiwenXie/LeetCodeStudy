package SkillCategory.array.preIndexSum;

import java.util.HashMap;

/**
 * @author ywxie
 * @date 2022/4/19 14:44
 * @describe
 */
public class ReSolutions {
    /**
     * 303. 区域和检索 - 数组不可变
     * ok
     */
    class NumArray {

        private int[] presSum;

        public NumArray(int[] nums) {
            presSum = new int[nums.length  + 1];
            for (int i = 1; i < nums.length + 1; i++) {
                presSum[i] = presSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return presSum[right + 1] - presSum[left];
        }
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     * 需要多练习
     */
    class NumMatrix {

        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            preSum = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
        }
    }

    /**
     * 560. 和为 K 的子数组
     */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        int sum0_i = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            sum0_i += nums[i];
            int sum0_j = sum0_i - k;
            if (hashMap.containsKey(sum0_j)){
                result += hashMap.get(sum0_j);
            }
            // 注意：把前缀和 nums[0..i] 加入并记录出现次数
            hashMap.put(sum0_i, hashMap.getOrDefault(sum0_i, 0) + 1);
        }
        return result;
    }
}
