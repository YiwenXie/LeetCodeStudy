package SkillCategory.tree;

/**
 * @author ywxie
 * @date 2022/5/12 17:14
 * @describe
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    // 以该节点为根的树的节点总数
    public int size;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
