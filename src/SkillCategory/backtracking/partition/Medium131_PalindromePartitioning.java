package SkillCategory.backtracking.partition;

import java.util.ArrayList;
import java.util.List;

/**
 * 其实切割问题类似组合问题。
 * <p>
 * 例如对于字符串abcdef：
 * <p>
 * 组合问题：选取一个a之后，在bcdef中再去选取第二个，选取b之后在cdef中再选取第三个.....。
 * 切割问题：切割一个a之后，在bcdef中再去切割第二段，切割b之后在cdef中再切割第三段.....。
 */
public class Medium131_PalindromePartitioning {
    List<List<String>> result;
    // path用来记录切割过的回文子串。
    List<String> path;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        recursion(0, s);
        return result;
    }

    /**
     * @param startIndex 表示下一轮递归遍历的起始位置，这个startIndex就是切割线。
     * @param s
     */
    private void recursion(int startIndex, String s) {
        // // 如果起始位置已经大于s的大小，说明已经找到了一组分割方案了
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // iterate over the range from startIndex
        for (int i = startIndex; i < s.length(); i++) {
            // [startIndex, i] 就是当前要截取的子串
            String s1 = s.substring(startIndex, i + 1);
            // 判断这个子串是不是回文
            if (isPalindrome(s1)) {
                // 如果是回文，就加入path
                path.add(s1);
            } else {
                // 不是回文，加入下一个字母继续判断字串[startIndex, i + 1]是不是回文
                continue;
            }
            // 寻找i+1为起始位置的子串
            recursion(i + 1, s);
            // 回溯过程，弹出本次已经添加的子串
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
