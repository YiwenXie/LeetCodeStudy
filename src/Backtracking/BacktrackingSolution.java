package Backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ywxie
 * @date 2020/12/16 15:06
 * @describe 回溯算法
 * for 选择 in 选择列表:
 *     # 做选择
 *     将该选择从选择列表移除
 *     路径.add(选择)
 *     backtrack(路径, 选择列表)
 *     # 撤销选择
 *     路径.remove(选择)
 *     将该选择再加入选择列表
 */
public class BacktrackingSolution {

    /**
     * 46. 全排列
     * 后序遍历
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        permuteHelper(nums, track, res);
        return res;
    }

    private void permuteHelper(int[] nums, LinkedList<Integer> track, List<List<Integer>> res){
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (track.contains(nums[i])){
                continue;
            }
            track.add(nums[i]);
            permuteHelper(nums, track, res);
            track.removeLast();
        }
    }
}
