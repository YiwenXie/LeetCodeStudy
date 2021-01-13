package listnode;

/**
 * @author ywxie
 * @date 2021/1/13 15:48
 * @describe 链表
 */
public class ListNode {

    int val;
    ListNode next;
    ListNode prev;
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
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return node.next;
    }

    /**
     * 707. 设计链表
     *  * Your MyLinkedList object will be instantiated and called as such:
     *  * MyLinkedList obj = new MyLinkedList();
     *  * int param_1 = obj.get(index);
     *  * obj.addAtHead(val);
     *  * obj.addAtTail(val);
     *  * obj.addAtIndex(index,val);
     *  * obj.deleteAtIndex(index);
     */
    class MyLinkedList {
        int size;
        ListNode head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
            //设置一个虚拟头节点
            size = 0;
            head = new ListNode(0);
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if (index >= size || index < 0){
                return -1;
            }
            ListNode cur = head;
            for (int i = 0; i <= index; i++){
                cur = cur.next;
            }
            return cur.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
//            addAtIndex(0, val);
            ListNode node = new ListNode(val);
            node.next = head.next;
            head.next = node;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
//            addAtIndex(size, val);
            ListNode node = new ListNode(val);
            ListNode cur = head;
            while (cur.next != null){
                cur = cur.next;
            }
            cur.next = node;
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if (index > size){
                return;
            }
            ListNode node = new ListNode(val);
            ListNode cur = head;
            for (int i = 0; i < index; i++){
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
            size++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if (index > size || index < 0){
                return;
            }
            ListNode cur = head;
            for (int i = 0; i < index; i++){
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }
    }

}
