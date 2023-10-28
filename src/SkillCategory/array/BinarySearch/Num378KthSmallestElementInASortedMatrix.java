package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/10/28 15:59
 */
public class Num378KthSmallestElementInASortedMatrix {

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
            // 找到一个最小的count使得这个表达式成立
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
            // 这里就是在计数比m要大的最小的数有多少
            // 返回行下标用于累加
            count += template.upperBound(nums, m, 0, nums.length);
        }
        return count;
    }
}
