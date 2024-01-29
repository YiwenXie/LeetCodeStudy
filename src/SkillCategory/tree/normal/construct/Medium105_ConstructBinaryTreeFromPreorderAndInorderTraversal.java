package SkillCategory.tree.normal.construct;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;

public class Medium105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return toConstruct(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode toConstruct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd || inStart >= inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int rootIndex = map.get(rootVal);
        int leftLength = rootIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = toConstruct(preorder, preStart + 1, preStart + leftLength + 1,
                inorder, inStart, rootIndex);
        root.right = toConstruct(preorder, preStart + leftLength + 1, preEnd,
                inorder, rootIndex + 1, inEnd);
        return root;
    }
}
