package SkillCategory.dataconstructdesing;

import java.util.*;

public class Medium380_InsertDeleteGetRandom {
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            int index = list.size();
            list.add(val);
            map.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int valIndex = map.get(val);
            int lastVal = list.get(list.size() - 1);
            list.set(valIndex, lastVal);
            map.put(lastVal, valIndex);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
