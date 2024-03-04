package SkillCategory.backtracking.partition;

import java.util.ArrayList;
import java.util.List;

public class Medium93_RestoreIPAddresses {
    /**
     * Time Complexity: O(3^4)
     * IP地址最多包含4个数字，每个数字最多有3种可能的分割方式，
     * 则搜索树的最大深度为4，每个节点最多有3个子节点。
     * Space Complexity: O(n)
     */
    List<String> result;
    List<String> path;

    public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        recursion(0, s);
        return result;
    }

    private void recursion(int startIndex, String s) {
        if (path.size() == 4) {
            if (startIndex == s.length()) {
//                StringBuilder sb = new StringBuilder();
//                for (String str : path) {
//                    sb.append(str).append(".");
//                }
//                sb.deleteCharAt(sb.length() - 1);
                result.add(String.join(".", path));
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            if (!isValid(str)) {
                break;
            }
            path.add(str);
            recursion(i + 1, s);
            path.remove(path.size() - 1);
        }
    }

    private boolean isValid(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.length() > 1 && str.startsWith("0")) {
            return false;
        }
        return Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 255;
    }
}
