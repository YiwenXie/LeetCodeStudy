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
 *
 *  回溯法，一般可以解决如下几种问题：
 *     组合问题：N个数里面按一定规则找出k个数的集合
 *     排列问题：N个数按一定规则全排列，有几种排列方式
 *     切割问题：一个字符串按一定规则有几种切割方式
 *     子集问题：一个N个数的集合里有多少符合条件的子集
 *     棋盘问题：N皇后，解数独等等
 * 回溯法一般是在集合中递归搜索，集合的大小构成了树的宽度，递归的深度构成的树的深度。
 * 集合大小和孩子的数量是相等的！
 */
public class BacktrackingSolution {

    /**
     * 回溯算法模板框架如下:
     * 回溯算法中函数返回值一般为void
     * void backtracking(参数) {
     *     if (终止条件) {
     *         存放结果;
     *         return;
     *     }
     *
     * for循环就是遍历集合区间，可以理解一个节点有多少个孩子，这个for循环就执行多少次。
     *     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
     *         处理节点;
     *         backtracking(路径，选择列表); // 递归
     *         回溯，撤销处理结果
     *     }
     * }
     * 「for循环可以理解是横向遍历，backtracking（递归）就是纵向遍历」，这样就把这棵树全遍历完了，一般来说，搜索叶子节点就是找的其中一个结果了。
     */

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
