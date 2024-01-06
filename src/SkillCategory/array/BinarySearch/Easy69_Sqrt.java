package SkillCategory.array.BinarySearch;

/**
 * @author Yiwen Xie
 * @description 69. Sqrt(x)
 * @date 2023/10/25 21:52
 */
public class Easy69_Sqrt {

    public static void main(String[] args) {
        // Integer类取值和int 类型取值一致，取值范围是从-2147483648 至2147483647(-2^31至2^31-1) ，包括-2147483648 和2147483647这两个数字。
        int x = 2147483647;
        System.out.printf("案例输入参数:" + x);
        int result = mySqrt(x);
//        int result = mySqrt2(x);
        System.out.println();
        System.out.printf("输出结果：" + result);
    }

    /**
     * time complexity:0(log x)
     * space complexity:O(1)
     *
     * @param x 0 <= x <= 2^31 - 1
     * @return
     */
    public static int mySqrt(int x) {
        int left = 0;
        // 为什么这里一定是x而不是x+1，因为x <= 2^31 - 1，当入参就为2^31 - 1，x+1会导致超出int取值范围，return 错误答案-1
        int right = x;
        // 因为 left right 是闭区间，所以这里用<=
        while (left <= right) {
            int m = left + (right - left) / 2;
            // 因为x平方根的整数部分ans是满足 k^2 ≤ x 的最大 k 值，所以寻找的是右边界，if条件是 m * m > x
            // long 是因为x <= 2^31 - 1，担心出界
            if ((long) m * m > x) {
                // 因为 left,right 是闭区间,所以这里收缩右边界时需要-1
                right = m - 1;
            } else {
                left = m + 1;
            }
        }
        // 为什么这里要减1呢？因为寻找的是右边界
        // 因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
        // 见 StudySolutions0f704.searchRightBound
        return left - 1;
    }

    /**
     * @param x
     * @return
     */
    public static int mySqrt2(long x) {
        //long 数据类型是 64 位有符号的 Java 原始数据类型。 当对整数的计算结果可能超出 int 数据类型的范围时使用。
        //long 数据类型范围是 -9,223,372,036,854,775,808 至 9,223,372,036,854,775,807 ( -2^63 至 2^63-1 )。
        long left = 0;
        // 当x=2^31 - 1且入参为long类型时，right的初始是可以用x+1的，因为这样计算结果就不会有问题
        long right = x + 1;
        while (left < right) {
            long m = left + (right - left) / 2;
            // 因为x平方根的整数部分ans是满足 k^2 ≤ x 的最大 k 值，所以寻找的是右边界
            // long 是因为x <= 2^31 - 1，担心出界
            if ((long) m * m > x) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        // 为什么这里要减1呢？因为寻找的是右边界
        // 因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
        // 见 StudySolutions0f704.searchRightBound
        return Integer.valueOf(String.valueOf(left - 1));
    }
}
