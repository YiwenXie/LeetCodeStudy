package SkillCategory.DynamicProgramming.背包问题;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/9 9:24
 */
public class Medium416_PartitionEqualSubsetSum {
    /**
     * Solution: Prefix sum
     * 72 / 141 个通过的测试用例
     * @param nums
     * @return
     */
//    public boolean canPartition(int[] nums) {
//        // sort array from small to large
//        Arrays.sort(nums);
//        // query array's prefix sum
//        int n = nums.length;
//        int[] prefixSum = new int[n + 1];
//        for (int i = 1; i < n + 1; i++) {
//            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
//        }
//        // iterate over this prefixSum array from 1st index
//        for (int i = 1; i < n + 1; i++) {
//            // query nums[0..i - 1]'s sum
//            int a = prefixSum[i];
//            // in this case we need to find out the difference that nums[0..n - 1] minus nums[0..i - 1]
//            // index i partition nums array to two subset:1.nums[0..i - 1] 2.nums[i..n-1]
//            // query nums[i..n-1]'s sum = nums[0..n - 1] - nums[0..i - 1] = prefixSum[n] - prefixSum[i]
//            int b = prefixSum[n] - prefixSum[i];
//            // judgement these subset is equals
//            if (a == b){
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Solution: DP backpack
     * Time Complexity: O(n * sum)
     * Space Complexity: O(n * sum)
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        // declare a variable that represents nums array' sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // if sum is odd not divided by 2, it cannot be divided into two subset that value is equal
        if (sum % 2 != 0) {
            return false;
        }
        // sum variable is represents the subset sum
        sum = sum / 2;
        // declare a two dimension array dp[n + 1][sum + 1]
        // i is represents nums[i - 1]'s value
        // j is represents package's capacity = one subset value
        // for i items, current backpack's capacity is j
        // in this case dp[i][j] is represents if backpack can divide into two equal subset
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case is dp[..][0] = true and dp[0][..] = false，
        // if backpack don't have capacity, that means backpack is filled
        // if there don't have any item, backpack is empty
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // backpack don't have enough capacity to put i item in backpack
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // not put i item in backpack, so result is i - 1 's result
                    boolean a = dp[i - 1][j];
                    // put i item in backpack
                    boolean b = dp[i - 1][j - nums[i - 1]];
                    // have true is true, means backpack's capacity can divide into two p subset
                    dp[i][j] = a || b;
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * Solution: DP backpack
     * Time Complexity: O(n\*sum)
     * Space Complexity: O(sum)
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        // declare a variable that represents nums array' sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // if sum is odd not divided by 2, it cannot be divided into two subset that value is equal
        if (sum % 2 != 0) {
            return false;
        }
        // sum variable is represents the divided subset sum
        sum = sum / 2;
        // dp[j] represents when backpack's capacity is j, backpack can or cannot be filled?
        boolean[] dp = new boolean[sum + 1];
        // base case
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        // when backpack's capacity is sum, backpack can or cannot be filled?
        return dp[sum];
    }

    /**
     * Solution: DP backpack one dimensional array
     * Time Complexity: O(n\*sum)
     * Space Complexity: O(sum)
     *
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int n = nums.length;
        // declare a variable that represents nums array' sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // if sum is odd not divided by 2, it cannot be divided into two subset that value is equal
        if (sum % 2 != 0) {
            return false;
        }
        // sum variable is represents the divided subset sum
        sum = sum / 2;
        // dp[j] represents when backpack's capacity is j, the maximum value that backpack can be filled
        int[] dp = new int[sum + 1];
        // base case
        for (int i = sum; i >= nums[0]; i--) {
            dp[i] = nums[0];
        }
        // 如果使用一维dp数组，物品遍历的for循环放在外层，
        // 遍历背包的for循环放在内层，且内层for循环倒叙遍历！
        for (int i = 1; i < n; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
                if (dp[sum] == sum)
                    return true;
            }
        }
        // when backpack's capacity is sum, backpack can or cannot be filled?
        return dp[sum] == sum;
    }

    /**
     * Solution: DP backpack two-dimensional array
     * Time Complexity: O(n\*sum)
     * Space Complexity: O(sum)
     *
     * @param nums
     * @return
     */
    public boolean canPartition4(int[] nums) {
        int n = nums.length;
        // declare a variable that represents nums array' sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // if sum is odd not divided by 2, it cannot be divided into two subset that value is equal
        if (sum % 2 != 0) {
            return false;
        }
        // sum variable is represents the divided subset sum
        sum = sum / 2;
        // dp[j] represents when backpack's capacity is j, the maximum value that backpack can be filled
        int[][] dp = new int[n][sum + 1];
        // base case
        for (int i = sum; i >= nums[0]; i--) {
            dp[0][i] = nums[0];
        }
        // 如果使用一维dp数组，物品遍历的for循环放在外层，
        // 遍历背包的for循环放在内层，且内层for循环倒叙遍历！
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
                //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
                if (dp[i][sum] == sum)
                    return true;
            }
        }
        // when backpack's capacity is sum, backpack can or cannot be filled?
        return dp[n - 1][sum] == sum;
    }
}
