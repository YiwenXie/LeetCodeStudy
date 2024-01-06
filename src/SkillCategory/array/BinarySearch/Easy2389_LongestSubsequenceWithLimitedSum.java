package SkillCategory.array.BinarySearch;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/31 21:41
 */
public class Easy2389_LongestSubsequenceWithLimitedSum {
    /**
     * Solution: Sort + PrefixSum + Binary Search
     * Time complexity: O(nlogn + mlogn)
     * Space complexity: O(1)
     *
     * @param nums    integer array nums of length n
     * @param queries integer array queries of length m
     * @return an array answer of length m
     * where answer[i] is the maximum size of a subsequence
     * that you can take from nums
     * such that the sum of its elements is less than or equal to queries[i].
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        // nums 的元素次序对结果无影响，因此对 nums 从小到大进行排序
        Arrays.sort(nums);
        // 使用数组 prefixSum 保存 nums 的前缀和
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            // prefixSum[i] 表示前 i 个元素之和（不包括 nums[i]）
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = 1;
            int right = prefixSum.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 存在一个最小的mid使得prefixSum[mid] > queries[i]
                if (prefixSum[mid] > queries[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            // 虽然prefixSum中整体下标都往后移了一位，原本下标应该是left - 2
            // 但是因为result中保存的是长度，所以是left - 2 + 1 = left - 1
            result[i] = left - 1;
        }
        return result;
    }
}
