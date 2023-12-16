package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

public class Medium19RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // delete Nth from end need to find N+1th from end
        // set virtual head node
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        // find N+1th
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        // fast = N+1th from start
        // fast + x = sum = n + 1 + x = sum
        // slow = x = sum - n - 1
        // slow is N+1 node from end
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
