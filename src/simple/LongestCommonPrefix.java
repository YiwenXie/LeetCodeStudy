package simple;

/**
 * @author ywxie
 * @date 2020/7/17 21:53
 * @describe
 */
public class LongestCommonPrefix {

    static String longestCommonPrefix(String[] strs){
        if (strs.length== 0 || strs == null){
            return "";
        }
        String s1 = strs[0];
        for (int i = 0; i < s1.length(); i++){
            //获取原版字母
            char a = strs[0].charAt(i);
            //匹配单词
            for (int j = 1; j <strs.length; j++){
                //拿到比较字母
                if (i == strs[j].length() || a != strs[j].charAt(i)){
                    return strs[0].substring(0, i);
                }
                //先比较a是否等于b，容易越界
//                char b = strs[j].charAt(i);
//                if (a != b || i == strs[j].length()){
//                    return strs[0].substring(0, i);
//                }
            }
        }
        return strs[0];
    }
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        System.out.println("longest common prefix:" + longestCommonPrefix(strs));
        System.out.println("longest common prefix:" + longestCommonPrefix(strs2));
    }
}
