package SkillCategory.hashtable;

import java.util.HashMap;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 9:16
 */
public class Medium454_4SumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int sum = num1 + num2;
                hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (int num1 : nums3) {
            for (int num2 : nums4) {
                int sum = num1 + num2;
                int diff = -sum;
                if (hashMap.containsKey(diff)) {
                    count += hashMap.get(diff);
                }
            }
        }
        return count;
    }
}
