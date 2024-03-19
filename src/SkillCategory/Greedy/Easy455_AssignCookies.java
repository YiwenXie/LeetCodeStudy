package SkillCategory.Greedy;

import java.util.Arrays;

public class Easy455_AssignCookies {
    /**
     * Solution: sort + loop + dual pointer
     * Time Complexity: O(logn + logn + nlogn = (n + 1)logn = nlogn)
     * Space complexity: O(1)
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int nextStartIndex = 0;
        int result = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = nextStartIndex; j < s.length; j++) {
                if (s[j] >= g[i]) {
                    result++;
                    nextStartIndex = j + 1;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Solution: greedy + sort + dual pointer
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        // 大饼干先满足胃口大的孩子
        // Big biscuits first satisfy children with big appetites
        int index = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        // 小饼干先满足胃口小的孩子
        // Small biscuits first satisfy children with small appetites
//        int index = 0;
//        for (int i = 0; i < g.length; i--) {
//            if (index < s.length && s[index] >= g[i] ) {
//                result++;
//                index++;
//            }
//        }
        return result;
    }
}
