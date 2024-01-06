package SkillCategory.array.BinarySearch;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/28 15:59
 */
public class Medium378_KthSmallestElementInASortedMatrix {

    public static void main(String[] args) {
        Medium378_KthSmallestElementInASortedMatrix kthSmallest = new Medium378_KthSmallestElementInASortedMatrix();
        int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;
        System.out.println("matrix案例输入:" + Arrays.deepToString(matrix));
        System.out.println("k案例输入:" + k);
        int kth = kthSmallest.kthSmallest(matrix, k);
        System.out.println("测试结果:" + kth);
    }

    /**
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 300
     * -109 <= matrix[i][j] <= 109
     * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
     * 1 <= k <= n^2
     *
     * @param matrix
     * @param k
     * @return time complexity: O(nlogn)
     * space complexity: O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        // 最小的数
        int left = matrix[0][0];
        // 最大的数
        int right = matrix[matrix.length - 1][matrix[0].length - 1] + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // f(matrix, mid)返回不大于m的数有多少
            // 找到一个最小的mid使得f(matrix, mid)即mid排在第几>=k
            if (f(matrix, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int f(int[][] matrix, int m) {
        // count of elements <= m
        int count = 0;
        for (int[] nums : matrix
        ) {
            // 不大于m的数有多少
            count += upperBound(nums, m, 0, nums.length);
        }
        return count;
    }

    public int upperBound(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 返回 ≤ target的数有多少，即mid = left - 1，然后长度/元素个数 = index + 1 = left - 1 + 1 = left
        return left;
    }
}
