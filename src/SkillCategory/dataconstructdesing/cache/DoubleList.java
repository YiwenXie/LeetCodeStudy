package SkillCategory.dataconstructdesing.cache;

/**
 * @author ywxie
 * @date 2022/6/12 16:43
 * @describe 双链表数据结构
 * 我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久未使用的。
 */
public class DoubleList {
    /**
     * 头尾虚节点
     */
    private Node head, tail;
    /**
     * 链表元素个数
     */
    private int size;

    public DoubleList() {
        // 初始化双向链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.pre = tail;
        tail.next = head;
        size = 0;
    }

    /**
     * 在链表尾部添加节点X，时间 0(1)
     */
    public void addLast(Node x) {
        x.pre = tail.pre;
        x.next = tail;
        tail.pre.next = x;
        tail.pre = x;
        size++;
    }

    /**
     * 删除链表中的 x 节点（x 一定存在）
     * 由于是双链表且给的是目标 Node 节点，时间 O(1)
     */
    public void remove(Node x) {
        x.pre.next = x.next;
        x.next.pre = x.pre;
        size--;
    }

    /**
     * 删除链表中第一个节点，并返回该节点，时间 O(1)
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    /**
     * 返回链表长度，时间 O(1)
     */
    public int size() {
        return size;
    }


}
