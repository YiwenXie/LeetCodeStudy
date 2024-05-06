package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Medium652_FindDuplicateSubtrees {
    List<TreeNode> result;
    HashMap<String, Integer> map;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        result = new HashSet<>();
        result = new ArrayList<>();
        List<TreeNode> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        compare(root.left, root.right);
        list.addAll(result);
        return list;
    }

    private boolean compare(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return false;
        }
        if (node1 == null) {
            return false;
        }
        if (node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return compare(node1.left, node2) && compare(node1.right, node2)
                    && compare(node1, node2.left) && compare(node1, node2.right);
        }
        boolean left = compare(node1.left, node2.left);
        boolean right = compare(node1.right, node2.right);
        if (left && right) {
            result.add(node1);
        }
        return true;
    }
}
