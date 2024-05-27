package SkillCategory.DynamicProgramming.子序列问题;

public class Medium152_MaximumProductSubarray {
    public static void main(String[] args) {
        Medium152_MaximumProductSubarray medium152 = new Medium152_MaximumProductSubarray();
        int[] nums = new int[]{-2, 3, -4};
        System.out.println(medium152.maxProduct(nums));
    }

    /**
     * dp
     * 时间复杂度：O(N)，这里 N 表示数组的长度；
     * 空间复杂度：O(N)，使用了两个状态数组，每一个数组的规模是 N。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // represent [0..n - 1] max product
        // 牢记状态的定义，一定以下标 i 结尾，即：乘积数组中 nums[i] 必须被选取。
        // 用 0 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最小值；
        // 用 1 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最大值
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][1] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
            } else {
                dp[i][0] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][1] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
            }
        }
        int result = dp[0][1];
        for (int i = 1; i < n; i++) {
            result = Math.max(dp[i][1], result);
        }
        return result;
    }

    /**
     * 第 53 题思路
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int n = nums.length;

        int[] maxDP = new int[n];
        int[] minDP = new int[n];

        maxDP[0] = nums[0];
        minDP[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                maxDP[i] = Math.max(nums[i], maxDP[i - 1] * nums[i]);
                minDP[i] = Math.max(nums[i], minDP[i - 1] * nums[i]);
            } else {
                maxDP[i] = Math.max(nums[i], minDP[i - 1] * nums[i]);
                minDP[i] = Math.max(nums[i], maxDP[i - 1] * nums[i]);
            }
        }

        int result = maxDP[0];
        for (int i = 1; i < n; i++) {
            result = Math.max(maxDP[i], result);
        }
        return result;
    }
}
