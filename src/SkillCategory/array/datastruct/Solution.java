package SkillCategory.array.datastruct;

import java.util.*;

/**
 * @author ywxie
 * @date 2022/5/3 15:36
 * @describe
 */
public class Solution {

    /**
     * 380. O(1) 时间插入、删除和获取随机元素
     */
    class RandomizedSet {

        // 一个List用来存储元素的值
        private List<Integer> list;
        // 一个HashMap用来存储元素的索引和值
        private HashMap<Integer, Integer> valToIndex;
        private Random random;
        public RandomizedSet() {
            list = new ArrayList<>();
            valToIndex = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if(valToIndex.containsKey(val)){
                return false;
            }
            list.add(val);
            valToIndex.put(val, list.size()-1);
            return true;
        }

        public boolean remove(int val) {
            if(!valToIndex.containsKey(val)){
                return false;
            }
            int index = valToIndex.get(val);
            int last = list.get(list.size() - 1);
            list.set(index, last);
            list.remove(list.size() - 1);
            valToIndex.put(last, index);
            valToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    /**
     * 710. 黑名单中的随机数
     */
    // 记录映射界限
    int size;
    HashMap<Integer, Integer> map;
    Random random;
    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        random = new Random();
        size = n - blacklist.length;
        // 将黑名单映射到map中
        for(int item : blacklist){
            map.put(item, -1);
        }
        // 记录最后一个元素的索引，保存在映射界限左边的黑名单位置
        int last = n - 1;
        // 把黑名单中的数字都交换到了区间 [sz, N) 中，
        // 同时把 [0, sz) 中的黑名单数字映射到了正常数字。
        for(int item : blacklist){
            // 如果黑名单已在映射界限右边，则略过
            if(item >= size){
                continue;
            }
            // 如果当前应被映射索引对应的元素已在黑名单，则前移最后一位元素黑名单映射索引
            while(map.containsKey(last)){
                last--;
            }
            map.put(item, last);
            // 前移黑名单元素应该被映射到的索引
            last--;
        }
    }

    public int pick() {
        // 随机选取一个索引
        int index = random.nextInt(size);
        return map.getOrDefault(index, index);
        // 这个索引命中了黑名单，
        // 需要被映射到其他位置
//         if(map.containsKey(index)){
//             return map.get(index);
//         }
        // 若没命中黑名单，则直接返回
//         return index;
    }
}
