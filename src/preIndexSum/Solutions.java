package preIndexSum;

/**
 * @author ywxie
 * @date 2022/4/16 16:20
 * @describe
 */
public class Solutions {
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
}
