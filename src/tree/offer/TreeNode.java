package tree.offer;

/**
 * @author ywxie
 * @date 2020/12/22 13:55
 * @describe
 */
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;
    private TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     * 前序+中序遍历构造二叉树,分出左右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int pre_start, int pre_end, int[] inorder, int in_start, int in_end){
        if (pre_start >= pre_end){
            return null;
        }
        int rootVal = preorder[pre_start];
        TreeNode root = new TreeNode(rootVal);
        int in_root_index = 0;
        for (int i = in_start; i < in_end; i++){
            if (inorder[i] == rootVal){
                in_root_index = i;
                break;
            }
        }
        int leftNum = in_root_index - in_start;
        root.left = buildTreeHelper(preorder, pre_start+1, pre_start+1+leftNum, inorder, in_start, in_root_index);
        root.right = buildTreeHelper(preorder, pre_start+leftNum+1, pre_end, inorder, in_root_index+1, in_end);
        return root;
    }
}
