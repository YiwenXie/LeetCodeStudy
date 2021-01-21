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
     * 双指针法原地反转
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
     * 双指针法
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
     * for循环中的判断
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
     * 双指针法+整体反转+局部反转
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

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 局部反转+整体反转
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        reverse(chars, 0, n);
        reverse(chars, n, chars.length);
        reverse(chars, 0, chars.length);
        return String.valueOf(chars);
    }

    /**
     * 28. 实现 strStr()
     * KMP
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()){
            return 0;
        }
        if (haystack.isEmpty()){
            return -1;
        }
        char[] hay = haystack.toCharArray();
        char[] need = needle.toCharArray();
        int[] next = getNextArray(need.length, need);
        int j = -1; // // 因为next数组里记录的起始位置为-1
        for (int i = 0; i < hay.length; i++) { // 注意i就从0开始
            while(j >= 0 && hay[i] != need[j + 1]) { // 不匹配
                j = next[j]; // j 寻找之前匹配的位置
            }
            if (hay[i] == need[j + 1]) { // 匹配，j和i同时向后移动
                j++;
            }
            if (j == (need.length - 1) ) { // 文本串s里出现了模式串t
                return (i - need.length + 1);
            }
        }
        return -1;
    }

    private int[] getNextArray(int length, char[] chars){
        int j = -1;
        int[] next = new int[length];
        next[0] = j;
        for (int i = 1; i < length; i++){
            //如果前后缀不相同
            while (j >=0 && chars[i] != chars[j+1]){
                //向前回溯
                j = next[j];
            }
            //如果前后缀相同
            if (chars[i] == chars[j+1]){
                j++;
            }
            //将j（前缀的长度）赋给next[i]
            next[i] = j;
        }
        return next;
    }
}
