package SkillCategory.array.BinarySearch;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/10 21:57
 */
public class Medium1300_SumOfMutatedArrayClosestToTarget {

    public static void main(String[] args) {
        Medium1300_SumOfMutatedArrayClosestToTarget closestToTarget = new Medium1300_SumOfMutatedArrayClosestToTarget();
        int[] arr = new int[]{4, 9, 3};
        int target = 10;
        System.out.println("arr案例输入:" + Arrays.toString(arr));
        System.out.println("target案例输入:" + target);
        int kth = closestToTarget.findBestValue(arr, target);
        System.out.println("测试结果:" + kth);
    }

    public int findBestValue(int[] arr, int target) {
        int left = 0;
        int max = 0;
        for (int num : arr
        ) {
            max = Math.max(num, max);
        }
        int right = max + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // change all the integers larger than value in the given array to be equal to value,
            if (f(arr, mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // the sum of the array gets as close as possible (in absolute difference) to target.
        return Math.abs(f(arr, left) - target) < Math.abs(f(arr, left - 1) - target) ? left : left - 1;
    }

    public int f(int[] arr, int m) {
        int sum = 0;
        for (int num : arr
        ) {
            sum += Math.min(m, num);
        }
        return sum;
    }
}
