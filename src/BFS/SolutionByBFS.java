package BFS;

import java.util.*;

/**
 * @author ywxie
 * @date 2020/12/15 14:29
 * @describe
 */
public class SolutionByBFS {

    /**
     * 752. 打开转盘锁
     */
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        visited.addAll(Arrays.asList(deadends));

        Queue<String> queue = new LinkedList<>();
        int step = 0;
        queue.offer("0000");

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String nums = queue.poll();
                if (visited.contains(nums)){
                    continue;
                }
                if (target.equals(nums)){
                    return step;
                }
                for (int j = 0; j < 4; j++){
                    String up = plusOne(nums, j);
                    if (!visited.contains(up)){
                        queue.offer(up);
                    }
                    String down = minusOne(nums, j);
                    if (!visited.contains(down)){
                        queue.offer(down);
                    }
                }
                visited.add(nums);
            }
            step++;
        }
        return -1;
    }

    private String plusOne(String nums, int j){
        char[] ch = nums.toCharArray();
        if (ch[j] == '9'){
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    private String minusOne(String nums, int j){
        char[] ch = nums.toCharArray();
        if (ch[j] == '0'){
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }
}
