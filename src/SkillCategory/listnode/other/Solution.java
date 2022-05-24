package SkillCategory.listnode.other;

import SkillCategory.listnode.ListNode;

/**
 * @author ywxie
 * @date 2022/5/11 17:08
 * @describe
 */
public class Solution {

    /**
     * 234. 回文链表
     * 把原始链表反转存入一条新的链表，然后比较这两条链表是否相同。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode head2 = reverse(head);
        ListNode p1 = head;
        ListNode p2 = head2;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 234. 回文链表
     * 如果想倒序遍历链表，就可以在后序遍历位置操作
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    ListNode left;
    public boolean isPalindrome2(ListNode head) {
        left = head;
        return traverse(head);
    }

    // 实际上就是把链表节点放入一个栈，然后再拿出来，这时候元素顺序就是反的
    boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    /**
     * 234. 回文链表
     * 快慢指针+反转+递归
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     * 这种解法虽然高效，但破坏了输入链表的原始结构
     */
    public boolean isPalindrome3(ListNode head) {
        // 先通过 双指针技巧 中的快慢指针来找到链表的中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null){
            slow = slow.next;
        }
        // slow 指针现在指向链表中点

        // 从slow开始反转后面的链表
        ListNode right = reverse2(slow);
        // 开始比较回文串
        ListNode left = head;
        while (right != null){
            if (left.val != right.val){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public ListNode reverse2(ListNode head){
        ListNode pre,cur;
        pre = null;cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
