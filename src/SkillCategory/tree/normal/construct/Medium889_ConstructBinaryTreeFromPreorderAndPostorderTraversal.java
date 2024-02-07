package SkillCategory.tree.normal.construct;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;

public class Medium889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    HashMap<Integer, Integer> map;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        return toConstruct(preorder, 0, preorder.length, postorder, 0, postorder.length);
    }

    private TreeNode toConstruct(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart >= preEnd || postStart >= postEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        if (preStart + 1 >= preEnd) {
            return root;
        }
        int leftRootVal = preorder[preStart + 1];
        int leftRootIndex = map.get(leftRootVal);
//        int leftRootIndex = 0;
//        for (int i = postStart; i < postEnd; i++) {
//            if (postorder[i] == leftRootVal) {
//                leftRootIndex = i;
//                break;
//            }
//        }
        int leftLength = leftRootIndex - postStart + 1;
        root.left = toConstruct(preorder, preStart + 1, preStart + leftLength + 1,
                postorder, postStart, leftRootIndex + 1);
        root.right = toConstruct(preorder, preStart + leftLength + 1, preEnd,
                postorder, leftRootIndex + 1, postEnd);
        return root;
    }
}
