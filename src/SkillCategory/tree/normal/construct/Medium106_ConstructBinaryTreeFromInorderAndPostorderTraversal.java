package SkillCategory.tree.normal.construct;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;

public class Medium106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recursion(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode recursion(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart >= inEnd || postStart >= postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd - 1];
        int rootIndex = map.get(rootVal);
        int leftLength = rootIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = recursion(inorder, inStart, rootIndex,
                postorder, postStart, postStart + leftLength);
        root.right = recursion(inorder, rootIndex + 1, inEnd,
                postorder, postStart + leftLength, postEnd - 1);
        return root;
    }
}
