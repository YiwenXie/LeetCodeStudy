package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

import java.util.PriorityQueue;
import java.util.logging.Handler;

/**
 * @author ywxie
 * @date 2022/5/5 16:35
 * @describe
 */
public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return node.next;
    }

    /**
     * 21. 合并两个有序链表
     */
    ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null){
            if (p1.val > p2.val){
                p.next = p2;
                p2 = p2.next;
            }else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null){
            p.next = p1;
        }
        if (p2 != null){
            p.next = p2;
        }
        return head.next;
    }

    /**
     * 23.合并K个升序链表
     * 优先队列 pq 中的元素个数最多是 k，所以一次 poll 或者 add 方法的时间复杂度是 O(logk)；
     * 所有的链表节点都会被加入和弹出 pq，所以算法整体的时间复杂度是 O(Nlogk)
     * 其中 k 是链表的条数，N 是这些链表的节点总数。
     */
    ListNode mergeKLists(ListNode[] lists){
        // 创建虚拟头节点
        ListNode head = new ListNode(-1);
        ListNode p = head;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length,
                (ListNode l1, ListNode l2) -> {
                    return l1.val - l2.val;
                }
        );
        // 将 k 个链表的头结点加入最小堆
        for (ListNode node: lists) {
            if (node != null){
                priorityQueue.add(node);
            }
        }
        while (!priorityQueue.isEmpty()){
            // 获取最小节点，接到结果链表中
            ListNode node = priorityQueue.poll();
            p.next = node;
            if (node.next != null){
                priorityQueue.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return head.next;
    }

    /**
     * 单链表的倒数第 k 个节点
     */
    ListNode findFromEnd(ListNode head, int k){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = head;
        ListNode p2 = head;
        // p1 先走 k 步，剩余节点是 n - k 个（n是节点总数）
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        // p1 和 p2 同时走 n - k 步
        // p1 走到链表末尾的空指针时前进了 n - k 步
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        // p2 停留在第 n - k + 1 个节点上，即恰好停链表的倒数第 k 个节点上
        return p2;
    }

    /**
     *
     */


}
