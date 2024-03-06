package SkillCategory.listnode.other;

import SkillCategory.listnode.ListNode;

public class Medium2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int addVal = 0;
        while (p1 != null && p2 != null) {
            int sum = p1.val + p2.val + addVal;
            addVal = sum / 10;
            if (addVal >= 1) {
                sum = sum % 10;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 != null) {
            while (addVal != 0 && p1 != null) {
                int sum = p1.val + addVal;
                addVal = sum / 10;
                if (addVal >= 1) {
                    sum = sum % 10;
                }
                cur.next = new ListNode(sum);
                cur = cur.next;
                p1 = p1.next;
            }
            cur.next = p1;
        }
        if (p2 != null) {
            while (addVal != 0 && p2 != null) {
                int sum = p2.val + addVal;
                addVal = sum / 10;
                if (addVal >= 1) {
                    sum = sum % 10;
                }
                cur.next = new ListNode(sum);
                cur = cur.next;
                p2 = p2.next;
            }
            cur.next = p2;
        }
        while (addVal != 0) {
            int sum = addVal;
            addVal = sum / 10;
            if (addVal >= 1) {
                sum = sum % 10;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        return dummy.next;
    }


}
