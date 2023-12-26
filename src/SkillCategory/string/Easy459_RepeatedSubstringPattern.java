package SkillCategory.string;

public class Easy459_RepeatedSubstringPattern {

    /**
     * 假设字符串s是由s1+s2组成的，s+s后，str就变成了s1+s2+s1+s2，去掉首尾，破环了首尾的s1和s2，变成了s3+s2+s1+s4,
     * 此时str中间就是s2+s1,如果s是循环字串，也就是s1=s2,所以str中间的s2+s1就和原字符串相等。
     * 如果s不是循环字串，s1!=s2，那么s1+s2是不等于s2+s1的，也就是str中间不包含s
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

}
