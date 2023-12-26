package SkillCategory.string;

public class Easy541_ReverseStringII {
    /**
     * MySolution
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            int lastLength = s.length() - i;
            if (lastLength < k) {
                sb.append(reverseFromLtoR(s, i, s.length() - 1));
            } else if (lastLength < 2 * k) {
                sb.append(reverseFromLtoR(s, i, i + k - 1));
                sb.append(s, i + k, s.length());
            } else {
                sb.append(reverseFromLtoR(s, i, i + k - 1));
                sb.append(s, i + k + 1, i + 2 * k);
            }
            i += 2 * k;
        }
        return sb.toString();
    }

    private String reverseFromLtoR(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            sb.append(s.charAt(right--));
        }
        return sb.toString();
    }

    /**
     * optimal solution
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param s
     * @param k
     * @return
     */
    public String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(chars, i, Math.min(i + k, n) -1);
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}
