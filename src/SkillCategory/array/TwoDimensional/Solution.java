package SkillCategory.array.TwoDimensional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ywxie
 * @date 2022/4/28 15:18
 * @describe
 */
public class Solution {
    /**
     * 48. 旋转图像
     * （顺时针旋转90）
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = n - 1; j >= 0; j--) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[j][i];
//                matrix[j][i] = temp;
//            }
//        }
        // 然后反转二维矩阵的每一行
        for (int[] row: matrix
             ) {
            reverse(row);
        }
    }

    private void reverse(int[] arr){
        int i = 0, j = arr.length - 1;
        while (i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j++;
        }
    }

    /**
     * 48. 旋转图像
     * （逆时针旋转90）
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 沿左下到右上的对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i -1] = temp;
            }
        }
        // 然后反转二维矩阵的每一行
        for (int[] row: matrix
        ) {
            reverse(row);
        }
    }

    /**
     * 54.螺旋矩阵
     */
    List<Integer> spiralOrder(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int right_bound  = m - 1;
        int lower_bound  = n - 1;
        int upper_bound  = 0;
        int left_bound  = 0;
        List<Integer> result = new LinkedList<>();
        while (result.size() < m * n){
            // 在顶部从左向右遍历
            if (upper_bound <= lower_bound){
                for (int i = left_bound; i <= right_bound ; i++) {
                    result.add(matrix[upper_bound][i]);
                }
                // 上边界下移
                upper_bound++;
            }
            // 在右侧从上向下遍历
            if (left_bound <= right_bound){
                for (int i = upper_bound; i <= lower_bound; i++) {
                    result.add(matrix[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }
            // 在底部从右向左遍历
            if (upper_bound <= lower_bound){
                for (int i = right_bound; i >= left_bound ; i--) {
                    result.add(matrix[lower_bound][i]);
                }
                // 下边界上移
                lower_bound--;
            }
            // 在左侧从下向上遍历
            if (left_bound <= right_bound){
                for (int i = lower_bound; i >= upper_bound ; i--) {
                    result.add(matrix[i][left_bound]);
                }
                // 左边界右移
                left_bound++;
            }
        }
        return result;
    }

    /**
     * 59.螺旋矩阵II
     */
    int[][] generateMatrix(int n){
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int i = 1;
        while ( i <= n*n){
            if (up <= down){
                for (int j = left; j <= right; j++) {
                    res[up][j] = i++;
                }
                up++;
            }

            if (left <= right){
                for (int j = up; j <= down ; j++) {
                    res[j][right] = i++;
                }
                right--;
            }

            if (up <= down){
                for (int j = right; j >= left ; j--) {
                    res[down][j] = i++;
                }
                down--;
            }


            if (left <= right){
                for (int j = down; j >= up ; j--) {
                    res[j][left] = i++;
                }
                left++;
            }
        }
        return res;
    }



}
