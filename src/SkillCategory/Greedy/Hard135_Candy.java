package SkillCategory.Greedy;

import java.util.Arrays;

public class Hard135_Candy {
    public static void main(String[] args) {
        int[] ratings = new int[]{1,2,87,87,87,2,1};
        // 1 + 2 + 3 + 1 + 3 + 2 + 1 = 13
        // 3  2 1
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        // 从前往后遍历和左边比较确认右边孩子大的情况
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // 从后往前遍历和右边比较确认左边孩子大的情况
        // 为什么一定要从后往前遍历？
        // 因为比的是左边孩子比右边孩子大的情况，所以势必需要用到右边的比较结果
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 两种选择
                // 在右边的基础上+1，和原来已有的和左边孩子比较的结果
                // 一定要取max，以保证candies[i]只有取最大的才能既保持对左边candies[i - 1]的糖果多，也比右边candies[i + 1]的糖果多。
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
        }
        int totalCandy = 0;
        for (int candy : candies) {
            totalCandy += candy;
        }
        return totalCandy;
    }
}
