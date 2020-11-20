package simple;

import com.sun.xml.internal.ws.util.StringUtils;

import java.net.BindException;

/**
 * @author ywxie
 * @date 2020/11/19 16:02
 * @describe 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 */
public class lengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        if(s.isEmpty() || s.length() == 0){
            return 0;
        }
        int length = 0;
        for (int i = s.length()-1; i >= 0 ; i--) {
            if (s.charAt(i) == ' '){
                if (length == 0){
                    continue;
                }
                break;
            }
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        String s = "hello world    ";
        System.out.println(lengthOfLastWord(s));
    }
}
