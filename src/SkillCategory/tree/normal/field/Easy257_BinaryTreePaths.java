package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Easy257_BinaryTreePaths {
    final static String ARROW = "->";
    List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        traversal(root, new StringBuilder());
        return result;
    }

    private void traversal(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(sb.toString());
        }
        sb.append(ARROW);
        traversal(root.left, new StringBuilder(sb));
        traversal(root.right, new StringBuilder(sb));
    }
}
