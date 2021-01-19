package str;

/**
 * @author ywxie
 * @date 2021/1/19 13:59
 * @describe 字符串
 */
public class StringSolution {

    /**
     * 344. 反转字符串
     */
    public void reverseString(char[] s) {
        int p = s.length - 1;
        for (int i = 0; i < s.length / 2; i++){
            if (i < p){
                char temp = s[i];
                s[i] = s[p];
                s[p--] = temp;
            }
        }
    }

    /**
     * 541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     *
     *     如果剩余字符少于 k 个，则将剩余字符全部反转。
     *     如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i = 2 * k + i ){
            if (i + k < s.length()) {
                reverse(chars, i, i + k);
            } else {
                reverse(chars, i, s.length());
            }
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end){
        int p = end - 1;
        for (int i = start; i < end; i++){
            if (i < p){
                char temp = chars[i];
                chars[i] = chars[p];
                chars[p--] = temp;
            }
        }
    }

    /**
     * 剑指 Offer 05. 替换空格
     * 把字符串 s 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    public String replaceSpace2(String s) {
        char[] r = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r.length; i++){
            if (r[i] == ' '){
                sb.append("%20");
            }else {
                sb.append(r[i]);
            }
        }
        return sb.toString();
    }
}
