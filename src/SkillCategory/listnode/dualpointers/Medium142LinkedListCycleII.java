package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

public class Medium142LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // if listNode have cycle, slow and fast should be encountered
            if (slow == fast) {
                // if
                slow = head;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
