package SkillCategory.Sort;

import SkillCategory.listnode.ListNode;


public class Medium148_SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);

        Medium148_SortList medium148_sortList = new Medium148_SortList();
        System.out.println(medium148_sortList.sortList(head));
    }

    /**
     * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middleNode = findMiddleNode(head);
        ListNode next = middleNode.next;
        middleNode.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(next);
        return toMerge(left, right);
    }

    private ListNode findMiddleNode(ListNode head) {
        /* why fast = head.next
          if head node num is odd, for example [2, 4, 3, 1, 5]
              when traversal is end, slow will stop at middle node [3]
              so can split list node to [2, 4, 3] and [1, 5]
              then because of recursion, continue to split sublist [2, 4, 3]
              when traversal is end, slow will stop at middle node [4]
              so can split list node to [2, 4] and [3]
              continue to split sublist [2, 4], when traversal is end, slow will stop at node [2]
              so [2, 4] can be split to [2] and [4], no problem
          if head node num is even, for example [2, 4, 3, 1]
              when traversal is end, slow will stop at the left node of middle line [4]
              so can split list node to [2, 4] and [1, 5] -> [2][4] and [1][5]
         if fast = head
          if head node num is odd, for example [2, 4, 3, 1, 5]
              when traversal is end, slow will stop at middle node [3]
              so can split list node to [2, 4, 3] and [1, 5]
              then because of recursion, continue to split sublist [2, 4, 3]
              when traversal is end, slow will stop at middle node [4]
              so sublist [2, 4, 3] can be split to [2, 4] and [3]
              continue to split sublist [2, 4], when traversal is end, slow will stop at node [4]
              and then try to split, found cannot split [2, 4] because middle is 4 not 2
              so the sublist[2, 4] will cannot be split all the time
              so that will cause ultimate stack overflow error because unlimited recursion
          BUT, if head node num is even, for example [2, 4, 3, 1]
              when traversal is end, slow will stop at the right node of middle line [3]
              so can split list node to [2, 4, 3] sublist and [1] sublist
              then because of recursion, continue to split sublist [2, 4, 3]
              ...same process and same error again
          so fast must be start at head.next
         */
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode toMerge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode cur1 = left;
        ListNode cur2 = right;
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
