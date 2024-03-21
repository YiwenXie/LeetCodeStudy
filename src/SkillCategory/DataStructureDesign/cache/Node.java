package SkillCategory.DataStructureDesign.cache;

/**
 * @author ywxie
 * @date 2022/6/12 16:41
 * @describe 双链表节点数据结构
 */
public class Node {
    public int key;
    public int value;
    public Node pre;
    public Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
