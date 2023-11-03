package SkillCategory.BinarySearch;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/11/3 20:35
 */
public class Medium2187MinimumTimeToCompleteTrips {

    /**
     * Time complexity: O(nlogm), where m ~= 1e15
     * Space complexity: O(1)
     *
     * @param time
     * @param totalTrips
     * @return
     */
    public long minimumTime(int[] time, int totalTrips) {
        // minimum tripe needed time (one bus)
        long left = 1;
        // maximum tripe needed time of a bus in all buses
        long maxTrip = 0;
        for (int t : time
        ) {
            maxTrip = Math.max(t, maxTrip);
        }
        // maximum tripe needed time (all buses)
        long right = maxTrip * totalTrips + 1;
        while (left < right) {
            // total needed tripe time
            long mid = left + (right - left) / 2;
            // have minimum time required for all buses to complete at least totalTrips trips
            if (f(time, mid) >= totalTrips) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * get trips sum of all bus in required total time
     *
     * @param time
     * @param mid  total time
     * @return trips num
     */
    public long f(int[] time, long mid) {
        long count = 0;
        for (int t : time
        ) {
            // how many trips every bus can do
            count += mid / t;
        }
        return count;
    }
}
