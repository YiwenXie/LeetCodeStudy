package SkillCategory.listnode.other;

import SkillCategory.listnode.ListNode;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/12 18:48
 */
public class Easy203RemoveLinkedListElements {
    /**
     * Solution: Virtual head nodes
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
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

    /**
     * Solution: No Virtual head nodes
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
