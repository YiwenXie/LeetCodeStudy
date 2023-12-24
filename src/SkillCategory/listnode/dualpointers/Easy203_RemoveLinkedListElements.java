package SkillCategory.listnode.dualpointers;

import SkillCategory.listnode.ListNode;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/24 9:06
 */
public class Easy203_RemoveLinkedListElements {
    /**
     * Solution: dual pointer
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
