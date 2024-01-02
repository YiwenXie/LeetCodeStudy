package SkillCategory.queue;

import java.util.*;

public class Medium347_TopKFrequentElements {
    /**
     * 要统计元素出现频率 --> hashmap
     * 对频率排序 --> 优先级队列
     * 为什么不用快排呢， 使用快排要将map转换为vector的结构，然后对整个数组进行排序
     * 而这种场景下，我们其实只需要维护k个有序的序列就可以了，所以使用优先级队列是最优的。
     * 找出前K个高频元素 --> 小顶堆
     * 是使用小顶堆呢，还是大顶堆？
     * 有的同学一想，题目要求前 K 个高频元素，那么果断用大顶堆啊。
     * 那么问题来了，定义一个大小为k的大顶堆，在每次移动更新大顶堆的时候，每次弹出都把最大的元素弹出去了，那么怎么保留下来前K个高频元素呢。
     * 而且使用大顶堆就要把所有元素都进行排序，那能不能只排序k个元素呢？
     * 所以我们要用小顶堆，因为要统计最大前k个元素，只有小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // It is used to count the frequency of occurrence of elements
        // key is element，value is element‘s frequency
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        // Priority queue to sort the frequency of elements
        // 小顶堆，统计最大前k个元素 Small top heap, statistics of the largest k elements
        // 小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) { //小顶堆只需要维持k个元素有序
            //小顶堆元素个数小于k个时直接加
            if (priorityQueue.size() < k) {
                // 在优先队列中存储二元组(num, cnt),cnt表示元素值num在数组中的出现次数
                // 出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
                priorityQueue.offer(new int[]{entry.getKey(), entry.getValue()});
            } else {
                // 当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                if (!priorityQueue.isEmpty() && priorityQueue.peek()[1] < entry.getValue()) {
                    // 弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            // 依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
            result[i] = Objects.requireNonNull(priorityQueue.poll())[0];
        }
        return result;
    }
}
