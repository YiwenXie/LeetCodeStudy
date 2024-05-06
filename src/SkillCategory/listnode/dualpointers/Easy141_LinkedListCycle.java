package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

public class Easy141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
        // slow = head;
        // while (slow != fast) {
        //     slow = slow.next;
        //     fast = fast.next;
        // }
        // return slow;
    }
}
