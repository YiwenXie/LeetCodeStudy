package DataStructureCategory.str;

/**
 * @author ywxie
 * @date 2022/4/11 18:16
 * @describe
 */
public class ReviewStringSolution {

    /**
     * 反转列表
     * @param s
     */
    public void reverseString(char[] s) {
        int j = s.length -1;
        for (int i = 0; i < s.length /2; i++){
            if (i < j){
                char temp = s[i];
                s[i] = s[j];
                s[j--] = temp;
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
        char[] list = s.toCharArray();
        for (int i = 0; i < s.length(); i = 2 *k + 1){
            if (i + k < s.length()){
                reverse(list, i, i+k);
            }else {
                reverse(list, i, s.length());
            }
        }
        return String.valueOf(list);
    }

    private void reverse (char[] s, int left, int right){
        int j = right - 1;
        for (int i = left; i < right; i++){
            if (i < j){
                char temp = s[i];
                s[i] = s[j];
                s[j--] = temp;
            }
        }
    }


}
