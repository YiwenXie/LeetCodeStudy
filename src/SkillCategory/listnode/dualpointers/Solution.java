package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

import java.util.Comparator;
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
     * 双指针
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
     * 最小堆
     */
    ListNode mergeKLists(ListNode[] lists){
        if (lists == null){
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // 优先级队列，最小堆
//        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length,
//                (ListNode l1, ListNode l2) -> {
//                    return l1.val - l2.val;
//                }
//        );
        // 创建虚拟头节点
        ListNode head = new ListNode(-1);
        ListNode p = head;
        // 将 k 个链表的头结点加入最小堆
        for (ListNode node: lists) {
            // 对当前节点进行判断，防止lists里面出现空节点
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
     * 双指针
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
     * 19.删除链表的倒数第N个结点
     * 双指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        ListNode p1 = head;
//        for (int i = 0; i < n; i++) {
//            p1 = p1.next;
//        }
//        // 考虑首结点
//        if (p1 == null){
//            return head.next;
//        }
//        ListNode p2 = head;
//        while (p1.next != null){
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        p2.next = p2.next.next;
//        return dummy.next;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 需要考虑被移除的元素是首结点的情况。
        //虚拟头结点的技巧，也是为了防止出现空指针的情况，
        // 比如说链表总共有 5 个节点，题目就让你删除倒数第 5 个节点，
        // 也就是第一个节点，那按照算法逻辑，应该首先找到倒数第 6 个节点。
        // 但第一个节点前面已经没有节点了，这就会出错。
        ListNode p1 = findFromEnd(dummy, n + 1);
        p1.next = p1.next.next;
        return dummy.next;
    }

    /**
     * 876.链表中的中间结点
     * 快慢指针
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步，
     * 这样，当 fast 走到链表末尾时，slow 就指向了链表中点。
     * 需要注意的是，如果链表长度为偶数，也就是说中点有两个的时候，
     * 我们这个解法返回的节点是靠后的那个节点。
     */
    ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 判断链表是否包含环
     */
    boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 如果链表中含有环，如何计算这个环的起点？
     */
    ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast){
                break;
            }
        }
        if (fast == null || fast.next == null){
            return null;
        }
        slow = head;
        // 从相遇点继续前进 k - m 步，也恰好到达环起点。
        // 因为结合上图的 fast 指针，从相遇点开始走k步可以转回到相遇点，
        // 那走 k - m 步肯定就走到环起点了
        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

        // 另一种解法 用set
//        ListNode cur = head;
//        Set<ListNode> set = new HashSet<>();
//        while(cur != null){
//            if(set.contains(cur)){
//                return cur;
//            }else{
//                set.add(cur);
//                cur = cur.next;
//            }
//        }
//        return null;
    }

    /**
     * 160.相交链表
     * 我的思路：先反转链表，再连接a1和b1，最后用判断环起点来找到它们的相交点
     * 优化后思路：如果把两条链表首尾相连，那么「寻找两条链表的交点」的问题转换成了前面讲的「寻找环起点」的问题
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode b1 = new ListNode(-1);
        b1.next = headB;
        // 找到headB的最后一个结点，即公共链条部分的最后一个结点
        while (b1.next != null){
            b1 = b1.next;
        }
        // 将公共链条的最后一个结点与headB头结点相连，这样就构成了一个环
        b1.next = headB;
        // 利用快慢指针遍历headA，找出环的起点，即链表A与链表B相交点
        ListNode result = detectCycle(headA);
        // 因为题目要求函数返回结果后，链表必须保持其原始结构。
        // 所以这里回到之前把公共链表最后一个结点（即b1），把b1与链表B断开
        b1.next = null;
        // 返回刚刚找环起点的结果
        return result;
    }


}
