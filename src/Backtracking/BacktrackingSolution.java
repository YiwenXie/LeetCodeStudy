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
    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        permuteHelper(nums, track, res);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    private void permuteHelper(int[] nums, LinkedList<Integer> track, List<List<Integer>> res){
        // 触发结束条件
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            // 排除不合法的选择
            if (track.contains(nums[i])){
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            permuteHelper(nums, track, res);
            // 取消选择
            track.removeLast();
        }
    }
}
