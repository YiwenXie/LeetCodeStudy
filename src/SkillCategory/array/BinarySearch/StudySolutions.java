package SkillCategory.array.BinarySearch;

/**
 * @author ywxie
 * @date 2022/4/13 14:24
 * @describe
 */
public class StudySolutions {
    /**
     * 704 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html#_704-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            //计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有效防止了 left 和 right 太大，直接相加导致溢出的情况。
            int mid = left + (right - left) / 2;
            if(target > nums[mid]){
                left = mid + 1;
            }else if(target < nums[mid]){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置  ok-100过
     * 核心思想：有几个数比target小，寻找左边界
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid;
            }else {
                // 收缩右边界
                right = mid;
            }
        }
        // left和right都行，因为终止条件就是left = right
        return left;
    }

    /**
     * 875. 爱吃香蕉的珂珂
     * f(x) = target
     * f(x)：时间，即h
     * x: 自变量，速度，即k
     * target：H小时内
     * 思路：二分搜索法，找到中间变量
     *      1.确定函数
     *      2.确定找左右边界
     *      3.框架
     * 是关于X的单调递增函数，吃香蕉的速度越快，花费的时间就越少
     * 自变量 x：吃香蕉的速度
     * 目标值 target：H ，在H小时内吃完
     * f(x)：时间，即h，速度越快时间越少
     * X的最大值：香蕉的最大值
     * X的最小值：1，一次一根
     * 求X的最小值，即求 X 的左边界
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
//        int right = (int) (Math.pow(10.0, 9.0) + 1.0);
        int right = 1000000000 + 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (f(piles, mid) < h){
                // 花的时间更短，说明速度 X 过大了，要降低速度 X，那就收缩右边界
                right = mid;
            }else if (f(piles, mid) > h){
                // 花的时间更长，说明速度 X 过小了，要提高速度 X，那就收缩左边界
                left = mid + 1;
            }else {
                // 题目要求速度 X 最小，即搜索左侧边界，则收缩右边界
                right = mid;
            }
        }
        // 返回左边界（最小值）
        return left;
    }

    private int f(int[] piles, int x){
        int hours = 0;
        for (int pile : piles) {
            hours += pile / x;
            if (pile % x > 0) {
                hours++;
            }
        }
        return hours;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     * 是关于自变量x的单调函数f(x)
     * x:载力
     * target：目标days
     * f(x)：days
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 1;
        // 确定搜索区间left、right，分别是载重的最小值和最大值
        for (int weight : weights) {
            // 最小值为weights[i]的最大值（每次必须装一次货物走）
            left = Math.max(weight, left);
            // 最大值为weights[]的总和（一次性把货物装完）
            right += weight;
        }
        while (left < right){
            int mid = left + (right - left) / 2;
            if (fShipWithinDays(weights, mid) < days){
                right = mid;
            }else if (fShipWithinDays(weights, mid) > days){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    private int fShipWithinDays(int[] weights, int power) {
        int days = 0;
        for (int i = 0; i < weights.length;) {
            int cap = power;
            while (i < weights.length){
                if (cap < weights[i]){
                    // 没有载力了，第二天载力恢复再弄
                    break;
                }else {
                    // 计算剩余载力
                    cap -= weights[i];
                }
                // 货物增加
                i++;
            }
            // 需要恢复载力了
            days++;
        }
        return days;
    }

    /**
     * 410. 分割数组的最大值
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。（即最小载力）
     * 其实就是1011的变种题
     * 自变量 x：数组的最大值（即载力）
     * 目标值 target：m （即运货天数）
     * f(x)：分割的数组数量 （即一次运几次货）
     * X的最大值：数组内所有元素之和
     * X的最小值：数组内最大元素
     * 求 X 的左边界
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        return shipWithinDays(nums, m);
    }
}
