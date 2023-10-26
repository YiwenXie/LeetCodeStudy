package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/26 22:59
 */
public class Num875KokoEatingBananas {

    /**
     * 1 <= piles.length <= 104, piles.length <= h <= 109, 1 <= piles[i] <= 109
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int num : piles
        ) {
            right = Math.max(right, num);
        }
        right++;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 存在一个最小的mid 使得 f(piles, mid) <= h 成立
            // 题目要求：the minimum integer k such that she can eat all the bananas within h hours.
            // 即 存在最小的k使得函数在h小时内成立
            if (f(piles, mid) <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int f(int[] piles, int k) {
        int h = 0;
        for (int i = 0; i < piles.length; i++) {
            h += piles[i] / k;
            if (piles[i] % k > 0) {
                h++;
            }
        }
        return h;
    }
}
