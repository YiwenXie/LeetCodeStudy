package SkillCategory.Greedy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium406_QueueReconstructionByHeight {
    /**
     * 遇到两个维度权衡的时候，一定要先确定一个维度，再确定另一个维度。
     * 从局部排序到全局排序
     * 局部最优：优先按身高高的people的k来插入。插入操作过后的people满足队列属性
     * 全局最优：最后都做完插入操作，整个队列满足题目队列属性
     * 时间复杂度：O(nlog n + n^2)
     * 空间复杂度：O(n)
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // 排序前的people: [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
        Arrays.sort(people, ((o1, o2) -> {
            // if height is same
            // then k更大的在后面(k[i]代表前面有k个比height[i]大或者等于的)
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            // 按身高排序,身高更高的肯定在前面
            return o2[0] - o1[0];
        }));
        // 排序完的people： [[7,0], [7,1], [6,1], [5,0], [5,2]，[4,4]]

        List<int[]> queue = new ArrayList<>(people.length);
        for(int[] person : people) {
            // 將value插入到指定index裡
            queue.add(person[1], person);
            /*
             * 插入的过程：
             *  插入[7,0]：[[7,0]]
             *  插入[7,1]：[[7,0],[7,1]]
             *  插入[6,1]：[[7,0],[6,1],[7,1]]
             *  插入[5,0]：[[5,0],[7,0],[6,1],[7,1]]
             *  插入[5,2]：[[5,0],[7,0],[5,2],[6,1],[7,1]]
             *  插入[4,4]：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
             */
        }
        return queue.toArray(new int[people.length][]);
    }
}
