package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

public class Medium24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode firstNode = null;
        ListNode secondNode = null;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
        ListNode next = null;
        while (cur.next != null && cur.next.next != null) {
            next = cur.next.next.next;
            firstNode = cur.next;
            secondNode = cur.next.next;
            secondNode.next = firstNode;
            firstNode.next = next;
            cur.next = secondNode;
            cur = firstNode;
        }
        return dummy.next;
    }
}
