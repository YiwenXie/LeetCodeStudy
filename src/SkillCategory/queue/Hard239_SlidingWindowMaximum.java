package SkillCategory.queue;

import java.util.*;

public class Hard239_SlidingWindowMaximum {
    /**
     * Solution: priority queue exceed limit time
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() == k) {
                list.add(queue.peek());
                queue.remove(nums[i - k + 1]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Solution: definite a MaxQueue
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length - k + 1;
        //存放结果元素的数组
        int[] res = new int[len];
        MaxQueue maxQueue = new MaxQueue();
        int count = 0;
        for (int num : nums) {
            maxQueue.push(num);
            if (maxQueue.size == k) {
                res[count++] = maxQueue.getMaxValue();
                maxQueue.popFirst();
            }
        }
        return res;
    }

    /**
     * Sliding Window Maximum Queue
     */
    static class MaxQueue {
        /**
         * 存单调的，第一位为最大，单调递减队列
         */
        private Deque<Integer> deque;
        /**
         * 存正常的
         */
        private Queue<Integer> queue;

        public int size;

        public MaxQueue() {
            this.deque = new LinkedList<>();
            this.queue = new LinkedList<>();
            this.size = 0;
        }

        public int getMaxValue() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }

        public void push(int value) {
            // 如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，
            // 直到push元素的数值小于等于队列入口元素的数值为止
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.offerLast(value);
            queue.offer(value);
            size++;
        }

        public void popFirst() {
            if (deque.isEmpty() || queue.isEmpty()) {
                return;
            }
            int value = queue.poll();
            // 如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
            if (value == deque.peekFirst()) {
                deque.pollFirst();
            }
            size--;
        }
    }
}
