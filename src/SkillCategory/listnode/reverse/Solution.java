package SkillCategory.listnode.reverse;

import SkillCategory.listnode.ListNode;

/**
 * @author ywxie
 * @date 2022/5/9 13:23
 * @describe 不要跳进递归（你的脑袋能压几个栈呀？），
 * 而是要根据刚才的函数定义，来弄清楚这段代码会产生什么结果
 */
public class Solution {

    /**
     * 206.反转链表
     */
    ListNode reverse(ListNode head) {
        // 1、递归函数要有 base case
        // 如果链表为空或者只有一个节点的时候，反转结果就是它自己，直接返回即可。
        if (head == null || head.next == null){
            return head;
        }
        // 当链表递归反转之后，新的头结点是 last
        ListNode last = reverse(head.next);
        // 之前的 head 变成了最后一个节点
        head.next.next = head;
        // 别忘了链表的末尾要指向 null
        head.next = null;
        return last;
    }

    /**
     * 反转链表前N个节点
     */
    ListNode successor = null;// 后驱节点
    // 将链表的前 n 个节点反转（n <= 链表长度）
    ListNode reverseN(ListNode head, int n){
        // 1、base case 变为 n == 1，反转一个元素，就是它本身，同时要记录后驱节点
        if (n == 1){
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        // 2、刚才我们直接把 head.next 设置为 null，因为整个链表反转后原来的 head 变成了整个链表的最后一个节点。但现在 head 节点在递归反转之后不一定是最后一个节点了，所以要记录后驱 successor（第 n + 1 个节点），反转之后将 head 连接上。
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分
     */
    ListNode reverseBetween(ListNode head, int m, int n){
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }


    /**
     * 92.反转链表II
     */

}
