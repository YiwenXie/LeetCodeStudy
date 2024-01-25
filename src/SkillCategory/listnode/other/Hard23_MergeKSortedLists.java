package SkillCategory.listnode.other;

import SkillCategory.listnode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Hard23_MergeKSortedLists {
    /**
     * Solution: priority queue
     * n 是所有链表中元素的总和，k 是链表个数。
     * Time complexity: O(n * log(k))
     * Space complexity: O(n)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o.val)));
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                ListNode next = node.next;
                node.next = null;
                priorityQueue.add(next);
            }
        }
        return dummy.next;
    }

    /**
     * Solution: merge sort
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
