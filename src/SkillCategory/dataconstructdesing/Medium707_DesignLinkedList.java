package SkillCategory.dataconstructdesing;

import SkillCategory.listnode.ListNode;

public class Medium707_DesignLinkedList {
    class MyLinkedList {

        public ListNode dummyHead;
        public int size;


        public MyLinkedList() {
            dummyHead = new ListNode();
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index > size) {
                return -1;
            }
            ListNode cur = new ListNode();
            cur = dummyHead;
            for (int i = 0; i < index + 1; i++) {
                cur = cur.next;
            }
            return cur == null ? -1 : cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            ListNode pre = new ListNode();
            pre = dummyHead;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = pre.next;
            pre.next = newNode;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            ListNode cur = new ListNode();
            cur = dummyHead;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
