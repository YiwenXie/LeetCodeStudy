package SkillCategory.listnode.reverse;

import SkillCategory.listnode.ListNode;

public class Medium92_ReverseLinkedListII {
    /**
     * recursion
     * Time complexity: O(n)
     * Space complexity: O(log n)
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseToN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;

    private ListNode reverseToN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseToN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    /**
     * iterative approach
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode leftNode = pre.next;
        while (left > 1) {
            pre = pre.next;
            leftNode = leftNode.next;
            left--;
            right--;
        }
        ListNode rightNode = leftNode;
        while (right > 1) {
            rightNode = rightNode.next;
            right--;
        }
        ListNode next = rightNode.next;
        rightNode.next = null;
        pre.next = null;
        ListNode tempPre = null;
        ListNode cur = leftNode;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = tempPre;
            tempPre = cur;
            cur = temp;
        }
        pre.next = rightNode;
        leftNode.next = next;
        return dummy.next;
    }
}
