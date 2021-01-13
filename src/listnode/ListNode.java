package listnode;

/**
 * @author ywxie
 * @date 2021/1/13 15:48
 * @describe 链表
 */
public class ListNode {

    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    /**
     * 203. 移除链表元素
     * 递归
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next: head;
    }

    /**
     * 203. 移除链表元素
     * 设置虚拟头节点
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode cur = node;
        while (cur.next != null){
            if (cur.next.val == val){
                ListNode temp = cur.next;
                cur.next = cur.next.next;
                temp = null;
            }else {
                cur = cur.next;
            }
        }
        return node.next;
    }
}
