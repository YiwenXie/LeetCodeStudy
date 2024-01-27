package SkillCategory.listnode.reverse;

import SkillCategory.listnode.ListNode;

/**
 * @author Yiwen Xie
 * @description
 * @date 2023/12/12 18:56
 */
public class Easy206_ReverseLinkedList {
    /**
     * Solution: Dual Pointers
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * Solution: Recursion
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
