package DataStructureCategory.tree.offer;

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

    /**
     * 剑指 Offer 26. 树的子结构
     * 看前序遍历循序是否一致？
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //当 树 AAA 为空 或 树 BBB 为空 时，直接返回 falsefalsefalse ；
        if (A == null || B == null){
            return false;
        }
        //若树 BBB 是树 AAA 的子结构，则必满足以下三种情况之一，因此用或 || 连接；
        //    以 节点 AAA 为根节点的子树 包含树 BBB ，对应 recur(A, B)；
        //    树 BBB 是 树 AAA 左子树 的子结构，对应 isSubStructure(A.left, B)；
        //    树 BBB 是 树 AAA 右子树 的子结构，对应 isSubStructure(A.right, B)；
        return isSubStructure(A.left, B) || isSubStructure(A.right, B) || isSubStructureHelper(A, B);
    }

    private boolean isSubStructureHelper(TreeNode nodeA, TreeNode nodeB){
        //当节点 BBB 为空：说明树 BBB 已匹配完成（越过叶子节点），因此返回 true
        if (nodeB == null){
            return true;
        }
        //当节点 AAA 为空：说明已经越过树 AAA 叶子节点，即匹配失败，返回 false
        if (nodeA == null) {
            return false;
        }
        //当节点 AAA 和 BBB 的值不同：说明匹配失败，返回 falsefalsefalse
        //判断 AAA 和 BBB 的左子节点是否相等，即 recur(A.left, B.left)
        //判断 AAA 和 BBB 的右子节点是否相等，即 recur(A.right, B.right)
        return nodeA.val == nodeB.val && isSubStructureHelper(nodeA.left, nodeB.left) && isSubStructureHelper(nodeA.right, nodeB.right);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
