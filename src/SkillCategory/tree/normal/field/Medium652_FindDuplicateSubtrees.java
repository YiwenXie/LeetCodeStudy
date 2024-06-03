package SkillCategory.tree.normal.field;

import SkillCategory.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Medium652_FindDuplicateSubtrees {
    List<TreeNode> result;
    HashMap<String, Integer> map;

    /**
     * 时间复杂度：DFS 过程复杂度为 O(n)，对于每个子树需要构造出与子树同等规模的字符串，复杂度为 O(n)。
     * 整体复杂度为 O(n^2)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        result = new ArrayList<>();
        dfs(root);
        return result;
    }

    /**
     * 设计递归函数 String dfs(TreeNode root)，含义为返回以传入参数 root 为根节点的子树所对应的指纹标识。
     *
     * @param root
     * @return
     */
    private String dfs(TreeNode root) {
        // 对于标识的设计只需使用 "_" 分割不同的节点值，同时对空节点进行保留（定义为空串 " "）即可。
        if (root == null) {
            return " ";
        }
        String key = root.val + "-" + dfs(root.left) + dfs(root.right);
        map.put(key, map.getOrDefault(key, 0) + 1);
        // 使用哈希表记录每个标识（子树）出现次数，当出现次数为 2（首次判定为重复出现）时，将该节点加入答案。
        if (map.get(key) == 2) {
            result.add(root);
        }
        return key;
    }
}
