package SkillCategory.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/1 19:37
 */
public class Medium2226MaximumCandiesAllocatedToKChildren {
    /**
     * Solution:  Binary Search
     * Time complexity: O(nlogm) where n is number of piles, m is sum(candies) / k.
     * Space complexity: O(1)
     *
     * @param candies
     * @param k
     * @return
     */
    public int maximumCandies(int[] candies, long k) {
        long sum = 0;
        for (int num : candies
        ) {
            sum += num;
        }
        long left = 1;
        // 每个小孩最多也只能分到所有糖果数/k的数目，+1是为了开区间
        long right = sum / k + 1;
        while (left < right) {
            // 每个小孩可分到的最大糖果数目
            long mid = left + (right - left) / 2;
            // 为什么是< k时是收缩右边界
            // 1.fOfHua()返回的是能满足多少个小孩的要求
            // 2.当能满足的小孩的数量比要求少时，又因为mid是每个小孩可分到的最大糖果数目
            // 3.这说明mid还是太大，需要收缩右边界
            if (f(candies, mid, k) < k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // mid = left - 1
        return (int) (left - 1);
    }

    public long f(int[] candies, long mid, long k) {
        // 可以满足多少小孩
        long check = 0;
        for (int candy : candies
        ) {
            // 一个小优化
            // 当前袋子里糖果数量大于mid时才可以分糖果
            // 当前袋子里糖果数量<mid时直接跳过，因为分不了糖果
            if (candy >= mid) {
                // 计算一个袋子可以满足多少小孩
                check += candy / mid;
            }
        }
        return check;
    }
}
