package str;

import java.util.Arrays;

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

    /**
     * 151. 翻转字符串里的单词
     * 暴力逻辑判断法
     */
    public String reverseWords(String s) {
        if (s.isEmpty()){
            return " ";
        }
        String[] str = s.split(" ");
        if (str.length == 1){
            return Arrays.toString(str);
        }
        StringBuilder sb = new StringBuilder();
        boolean spaceFlag = false;
        for (int i = str.length - 1; i >= 0; i--){
            if (str[i].isEmpty()){
                spaceFlag = true;
                continue;
            }else if (!str[i].isEmpty() && i > 0) {
                if (spaceFlag){
                    sb.append(" ");
                }
                spaceFlag = false;
                sb.append(str[i]);
                if (!str[i-1].isEmpty()){
                    sb.append(" ");
                }
            }else if (!str[i].isEmpty() && i == 0){
                if (spaceFlag){
                    sb.append(" ");
                }
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 151. 翻转字符串里的单词
     * 双指针法
     */
    public String reverseWords2(String s){
        char[] chars = s.toCharArray();
        int p = chars.length;
        reverse(chars, 0, p);
        wordReverse(chars);
        return cleanSpace(chars, chars.length);
    }

    private void wordReverse(char[] chars){
        int i = 0;
        int j = 0;
        while (j < chars.length){
            while (i < chars.length && chars[i] == ' '){
                i++;
            }
            j = i;
            while (j < chars.length && chars[j] != ' '){
                j++;
            }
            reverse(chars, i, j);
            i = j;
        }
    }

    private String cleanSpace(char[] s_arr, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            while (j < n && s_arr[j] == ' ') {
                j++;
            }
            while (j < n && s_arr[j] != ' ') {
                s_arr[i++] = s_arr[j++];
            }
            while (j < n && s_arr[j] == ' ') {
                j++;
            }
            if (j < n) {
                s_arr[i++] = ' ';
            }
        }
        return new String(s_arr).substring(0, i);
    }
}
