package simple;

/**
 * @author ywxie
 * @date 2020/7/17 20:59
 * @describe
 */
public class Reverse {

    //我自己的解法 用字符串，但会超出范围，捕捉异常以取巧
    static int reverse(long x){
        String s = Integer.valueOf((int) x).toString();
        String s2 = s;
        String s3 = null;
        String reverse;
        if (s.contains("-")){
            s2 = s.replace("-","");
            s3 = "-";
        }
        if (s3 != null){
            reverse = new StringBuilder(s3).append(new StringBuilder(s2).reverse().toString()).toString();
        }else {
            reverse = new StringBuilder(s2).reverse().toString();
        }
        int reverse1 = 0;
        try {
            reverse1 = Integer.parseInt(reverse);
        }catch (NumberFormatException ignored){
            return 0;
        }
        return reverse1;
    }

    //别人的解法1
    public static int reverse2(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }

    //别人的解法2
    static int reverse3(int x){
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    public static void main(String[] args) {
        int i = 964632431;
        int res = reverse(i);
//        int res = reverse2(i);
//        int res = reverse3(i);
        System.out.printf("res = " + res);
    }
}
