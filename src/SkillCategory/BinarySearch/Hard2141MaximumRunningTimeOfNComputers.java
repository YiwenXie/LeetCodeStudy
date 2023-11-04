package SkillCategory.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/3 20:55
 */
public class Hard2141MaximumRunningTimeOfNComputers {

    /**
     * Solution: Binary Search + Check
     * Time complexity: O(Slogn) where S = sum(batteries)
     * Space complexity: O(1)
     *
     * @param n
     * @param batteries
     * @return
     */
    public long maxRunTime(int n, int[] batteries) {
        long left = 1;
        // every battery which can support all computer simultaneously power sum
        long right = 0;
        for (int battery : batteries
        ) {
            right += battery;
        }
        right++;
        while (left < right) {
            long mid = left + (right - left) / 2;
            //  check the total battery powers T = sum(min(m, batteries[i])), if T >= m * n,
            //  it means there is a way to run n computers for m minutes by fully unitize those batteries.
            // Proof: If T >= m*n holds, there are two cases:
            //    1.There are only n batteries, can not swap, but each of them has power >= m.
            //    2.At least one of the batteries have power less than m,
            //      but there are more than n batteries and total power is sufficient, we can swap them with others.
            // smallest m that does not fit.
            long T = f(batteries, mid);
            if (n * mid > T) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    /**
     * @param batteries
     * @param mid       minutes of all computers run time simultaneously
     * @return all required battery power sum(min ( m, batteries[i]))
     */
    public long f(int[] batteries, long mid) {
        long time = 0;
        for (int battery : batteries
        ) {
            // must assure all computers use different battery in every minute
            // that required battery power less than total time mid
            time += Math.min(battery, mid);
        }
        return time;
    }
}
