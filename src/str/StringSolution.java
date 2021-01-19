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
}
