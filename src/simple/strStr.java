package simple;

/**
 * @author ywxie
 * @date 2020/11/18 16:20
 * @describe 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class strStr {

    //傻瓜解法
    public static int strStr(String haystack, String needle) {
        return haystack.contains(needle)? haystack.indexOf(needle):-1;
    }

    //算法解法
    public static int strStr2(String haystack, String needle) {
        int index = -1;
        if (needle.isEmpty()){
            index = 0;
            return index;
        }
        if (needle.length() > haystack.length()){
            return index;
        }
        char[] hay = haystack.toCharArray();
        char[] need = needle.toCharArray();
        String str = "";
        int i = 0;
        int j = 0;
        while (i < haystack.length()){
            char a = hay[i];
            if (j < needle.length()){
                if (a == need[j]){
                    if (j == 0){
                        index = i;
                    }
                    str = str + a;
                    j++;
                    if (j >= needle.length()){
                        break;
                    }
                }else if (a != need[j] && j >0){
                    i = index;
                    j = 0;
                    str = "";
                    index = -1;
                }
            }
            i++;
        }
        return index;
    }

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr2(haystack, needle));
    }
}
