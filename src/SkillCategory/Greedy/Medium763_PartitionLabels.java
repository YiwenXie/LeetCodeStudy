package SkillCategory.Greedy;

import java.util.ArrayList;
import java.util.List;

public class Medium763_PartitionLabels {
    /**
     * 统计每一个字符最后出现的位置
     * 从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
     * 有点像跳青蛙，计算当前边界范围内能跳到的距离
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(hash[s.charAt(i) - 'a'], right);
            if (i == right) {
                list.add(right - left + 1);
                left = i + 1;
            }
        }
        return list;
    }
}
