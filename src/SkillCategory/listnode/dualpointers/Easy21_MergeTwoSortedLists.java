package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

public class Easy21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode p = dummy;
        while (cur1 != null && cur2 != null) {
            if (cur1.val > cur2.val) {
                p.next = cur2;
                cur2 = cur2.next;
            } else {
                p.next = cur1;
                cur1 = cur1.next;
            }
            p = p.next;
        }
        if (cur1 != null) {
            p.next = cur1;
        }
        if (cur2 != null) {
            p.next = cur2;
        }
        return dummy.next;
    }
}
