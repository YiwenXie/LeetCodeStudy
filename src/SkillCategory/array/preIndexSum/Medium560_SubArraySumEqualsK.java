package SkillCategory.array.preIndexSum;

import java.util.HashMap;

public class Medium560_SubArraySumEqualsK {
    /**
     * Brute Force
     * Time Complexity: O(n + n^2 = n * (n + 1))
     * Space Complexity: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        int value = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            if (prefixSum[i] == k) {
                value++;
            }
            for (int j = i + 1; j <= n; j++) {
                int diff = prefixSum[j] - prefixSum[i];
                if (diff == k) {
                    value++;
                }
            }
        }
        return value;
    }

    /**
     * Optimal: HashTable + prefixSum Array
     * Time Complexity: O(n)
     * Space Complexity: O(2n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        int value = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            if (prefixSum[i] == k) {
                value++;
            }
            int diff = prefixSum[i] - k;
            if (map.containsKey(diff)) {
                value += map.get(diff);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return value;
    }

    /**
     * Optimal2: HashTable
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum3(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 即 prefixSum == k
        map.put(0, 1);
        int value = 0;
        int prefixSum = 0;
        for (int i = 0; i <= n; i++) {
            prefixSum += nums[i];
            // 即 diff = 0 =》map.put(0, 1);
//            if (prefixSum == k) {
//                value++;
//            }
            int diff = prefixSum - k;
            if (map.containsKey(diff)) {
                value += map.get(diff);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return value;
    }
}
