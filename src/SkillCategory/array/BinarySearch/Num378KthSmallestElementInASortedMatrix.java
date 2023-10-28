package SkillCategory.array.BinarySearch;

import java.util.Arrays;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/28 15:59
 */
public class Num378KthSmallestElementInASortedMatrix {

    public static void main(String[] args) {
        Num378KthSmallestElementInASortedMatrix kthSmallest = new Num378KthSmallestElementInASortedMatrix();
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
            // f(matrix, mid)返回在m左边的数有多少个，
            // 找到一个最小的count使得count>=k
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
            BinarySearchTemplate template = new BinarySearchTemplate();
            // 这里就是在计数在m左边的数有多少
            // 返回行下标用于累加（返回m所在下标，就是m左边的数有多少）
            count += template.upperBound(nums, m, 0, nums.length);
        }
        // 返回在m左边的数有多少个
        return count;
    }
}
