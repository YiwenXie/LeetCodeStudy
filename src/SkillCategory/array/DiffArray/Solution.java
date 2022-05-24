package SkillCategory.array.DiffArray;

/**
 * @author ywxie
 * @date 2022/4/26 16:53
 * @describe
 */
public class Solution {

    class Difference{
        private int[] diff;

        public Difference(int[] nums) {
            diff = new int[nums.length];
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        public void increment(int i, int j, int val){
            diff[i] +=  val;
            if (j + 1 < diff.length){
                diff[j + 1] -= val;
            }
        }

        public int[] result(){
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }

    /**
     * 370.区间加法
     */
    int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        Difference difference = new Difference(nums);
        for (int[] update:
             updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            difference.increment(i , j, val);
        }
        return difference.result();
    }

    /**
     * 1109. 航班预订统计
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference difference = new Difference(nums);
        for (int[] booking:bookings
             ) {
            int i = booking[0];
            int j = booking[1];
            int val = booking[3];
            difference.increment(i - 1, j - 1, val);
        }
        return difference.result();
    }

    /**
     * 1094. 拼车
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference difference = new Difference(nums);
        for (int[] trip: trips
             ) {
            int i = trip[1];
            int j = trip[2] - 1;
            int val = trip[0];
            difference.increment(i, j, val);
        }
        int[] result = difference.result();
        for (int i = 0; i < result.length; i++) {
            if (result[i] > capacity){
                return false;
            }
        }
        return true;
    }
}
