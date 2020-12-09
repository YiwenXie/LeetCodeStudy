package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywxie
 * @date 2020/12/9 13:49
 * @describe
 */
public class simple {

    /**
     * 509. 斐波那契数
     * 递归 O(2^n)
     */
    public int fib(int N) {
        if(N == 0){
            return 0;
        }
        if(N == 1 || N == 2){
            return 1;
        }
        return fib(N - 1) + fib (N - 2);
    }

    /**
     * 509. 斐波那契数
     * 递归\时间复杂度是 O(n) 状态转移方程
     */
    public int fib2(int N) {
        if(N == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        return doFib(N, map);
    }

    private int doFib(int N, Map<Integer, Integer> map){
        if(N == 1 || N == 2){
            return 1;
        }
        if (map.containsKey(N)){
            return map.get(N);
        }
        map.put(N,doFib(N-1, map) + doFib(N-2, map));
        return map.get(N);
    }

    /**
     * 509. 斐波那契数
     * 递归\时间复杂度是 O(n) 空间复杂度降为 O(1) 「状态压缩」
     */
    public int fib3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 2 || N == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= N; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
