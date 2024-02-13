package SkillCategory.tree.bst.basic;

import SkillCategory.tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Easy501_FindModeInBinarySearchTree {
    HashMap<Integer, Integer> keyToFreq = new HashMap<>();
    HashMap<Integer, LinkedHashSet<Integer>> freqToKey = new HashMap<>();
    int maxFreq = 0;

    public int[] findMode(TreeNode root) {
        recursion(root);
        return freqToKey.get(maxFreq).stream().mapToInt(x -> x).toArray();
    }

    private void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        keyToFreq.put(root.val, keyToFreq.getOrDefault(root.val, 0) + 1);
        int key = root.val;
        int freq = keyToFreq.get(root.val);
        maxFreq = Math.max(freq, maxFreq);
        freqToKey.putIfAbsent(freq, new LinkedHashSet<Integer>());
        freqToKey.get(freq).add(key);
        // this code can be deleted
//        int oldFreq = freq - 1;
//        if (freqToKey.containsKey(oldFreq)) {
//            freqToKey.get(oldFreq).remove(key);
//        }
        recursion(root.right);
    }

    int resultFreq = 0;
    Set<Integer> result = new HashSet<>();

    public int[] findMode2(TreeNode root) {
        recursion2(root);
        return result.stream().mapToInt(x -> x).toArray();
    }

    TreeNode pre = null;

    private void recursion2(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion2(root.left);
        if (pre != null && pre.val == root.val) {
            maxFreq++;
        } else {
            maxFreq = 1;
        }
        if (resultFreq < maxFreq) {
            result.clear();
            resultFreq = maxFreq;
        }
        if (resultFreq == maxFreq) {
            result.add(root.val);
        }
        pre = root;
        recursion2(root.right);
    }
}
