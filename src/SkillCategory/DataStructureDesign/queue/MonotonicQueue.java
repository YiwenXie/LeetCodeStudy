package SkillCategory.DataStructureDesign.queue;

import java.util.LinkedList;

/**
 * @author ywxie
 * @date 2022/7/27 17:15
 * @describe 单调队列
 */
/* 单调队列的通用实现，可以高效维护最大值和最小值 */
public class MonotonicQueue<E extends Comparable<E>> {
    LinkedList<Integer> maxq = new LinkedList<>();

    public void push(int n) {
        // 将小于 n 的元素全部删除
        while (!maxq.isEmpty() && maxq.getLast() < n) {
            maxq.pollLast();
        }
        // 然后将 n 加入尾部
        maxq.addLast(n);
    }

    public int max() {
        return maxq.isEmpty() ? -1 : maxq.getFirst();
    }

    public void pop(int n) {
        if (n == maxq.getFirst()) {
            maxq.removeFirst();
        }
    }
}
