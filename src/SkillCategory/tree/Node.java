package SkillCategory.tree;

/**
 * @author ywxie
 * @date 2022/5/16 14:41
 * @describe
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node parent;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public Node(int val, Node left, Node right, Node next, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
        this.parent = parent;
    }
}