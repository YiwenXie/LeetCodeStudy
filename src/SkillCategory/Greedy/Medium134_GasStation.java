package SkillCategory.Greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Medium134_GasStation {
    public static void main(String[] args) {
        int[] gas = new int[]{5, 8, 2, 8};
        int[] cost = new int[]{6, 5, 6, 6};
        System.out.println(canCompleteCircuit(gas, cost));
    }
    /**
     * 32/40 not meet the requirement about circuit
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        HashMap<int[], Integer> map = new HashMap<>();
        // int[i] : 0 gas 1 cost
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int[] array = new int[]{gas[i], cost[i]};
            map.put(array, i);
            queue.add(array);
        }
        int[] start = queue.poll();
        int gasTank = start[0];
        int costStation = start[1];
        System.out.println("Start at station " + map.get(start) + ". Your tank = " + gasTank);
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            System.out.println("Travel to station " + map.get(next) + ". Your tank = " + gasTank + " - " + costStation + " + " + next[0]);
            gasTank = gasTank - costStation + next[0];
            System.out.println("Your tank = " + gasTank);
            if (gasTank < 0) {
                return -1;
            }
            costStation = next[1];
        }
        int left = gasTank - costStation;
        System.out.println("Travel to station " + map.get(start) + ". Your tank = " + gasTank + " - " + costStation + " = " + left);
        if (left < 0) {
            return -1;
        }
        return map.get(start);
    }

    /**
     * brute force
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            int rest = gas[i] - cost[i]; // 记录剩余油量
            int index = (i + 1) % cost.length;
            while (rest > 0 && index != i) { // 模拟以i为起点行驶一圈（如果有rest==0，那么答案就不唯一了）
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.length;
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) return i;
        }
        return -1;
    }

    /**
     * greedy
     * 首先如果总油量减去总消耗大于等于零那么一定可以跑完一圈，说明 各个站点的加油站 剩油量rest[i]相加一定是大于等于零的。
     * 每个加油站的剩余量rest[i]为gas[i] - cost[i]。
     * i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，
     * 因为这个区间选择任何一个位置作为起点，到i这里都会断油，那么起始位置从i+1算起，再从0计算curSum。
     * 那么局部最优：当前累加rest[i]的和curSum一旦小于0，起始位置至少要是i+1，因为从i之前开始一定不行。
     * 全局最优：找到可以跑一圈的起始位置。
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;
        int currentSum = 0;
        int totalSum = 0;
        int startIndex = 0;
        for (int i = 0; i < n; i++) {
            currentSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (currentSum < 0) {
                startIndex = i + 1;
                currentSum = 0;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return startIndex;
    }
}
