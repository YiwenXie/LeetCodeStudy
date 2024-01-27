package SkillCategory.listnode.reverse;

import SkillCategory.listnode.ListNode;

public class Hard25_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        if (k == 1) {
            return reverse(head);
        }
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            if (cur == null) {
                return head;
            }
            cur = cur.next;
        }
        ListNode next = cur;
        ListNode newHead = reverseLeftToRight(head, cur);
        head.next = reverseKGroup(next, k);
        return newHead;
    }

    private ListNode reverseLeftToRight(ListNode left, ListNode right) {
        ListNode pre = null;
        ListNode next;
        ListNode cur = left;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
