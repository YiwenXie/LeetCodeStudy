package Backtracking;

import java.util.*;

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

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     */
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        //类似于N叉树的递归遍历
//        for (int i = startIndex; i <= n; i++){
            // 剪枝优化
            // 可以剪枝的地方就在递归中每一层的for循环所选择的起始位置。
            // 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了
            // +1是因为是左闭区间
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){// 优化的地方
            path.add(i);// 处理节点
            combineHelper(n, k, i + 1);// 递归
            path.removeLast();// 回溯，撤销处理的节点
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3Helper(k, n, 1, 0);
        return result;
    }

    private void combinationSum3Helper(int k, int n, int startIndex, int sum){
        if (sum > n){// 剪枝优化
            return;
        }
        if (path.size() == k){
            if (sum == n){
                result.add(new ArrayList<>(path));
            }
            // 如果path.size() == k 但sum != targetSum 直接返回
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++){// 剪枝优化，如77
//        for (int i = startIndex; i <= 9; i++){
            path.add(i);
            sum += i;
            combinationSum3Helper(k, n, i + 1, sum);
            sum -= i;
            path.removeLast();
        }
    }

    /**
     * 17. 电话号码的字母组合
     */
    List<String> result17 = new ArrayList<>();
    char[] temp;
    HashMap<Integer, char[]> numberMap = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()){
            return result17;
        }
        char[] chars = digits.toCharArray();
        temp = new char[digits.length()];
        getLetterMap();
        letterCombinationsHelper(chars, chars.length, 0);
        return result17;
    }

    private void letterCombinationsHelper(char[] chars, int k, int index){
        //index 为深度，letters.length为宽度
        if (index == k){
            result17.add(new String(temp));
        }
        int j = chars[index] - '0';
        char[] letters = numberMap.get(j);
        for (int i = 0; i < letters.length; i++){
            temp[index] = letters[i];
            letterCombinationsHelper(chars, k, index + 1);
        }
    }

    private void getLetterMap(){
        numberMap.put(2, new char[]{'a', 'b', 'c'});
        numberMap.put(3, new char[]{'d', 'e', 'f'});
        numberMap.put(4, new char[]{'g', 'h', 'i'});
        numberMap.put(5, new char[]{'j', 'k', 'l'});
        numberMap.put(6, new char[]{'m', 'n', 'o'});
        numberMap.put(7, new char[]{'p', 'q', 'r', 's'});
        numberMap.put(8, new char[]{'t', 'u', 'v'});
        numberMap.put(9, new char[]{'w', 'x', 'y', 'z'});
//        switch (number){
//            case '2': return new char[]{'a', 'b', 'c'};
//            case '3': return new char[]{'d', 'e', 'f'};
//            case '4': return new char[]{'g', 'h', 'i'};
//            case '5': return new char[]{'j', 'k', 'l'};
//            case '6': return new char[]{'m', 'n', 'o'};
//            case '7': return new char[]{'p', 'q', 'r', 's'};
//            case '8': return new char[]{'t', 'u', 'v'};
//            case '9': return new char[]{'w', 'x', 'y', 'z'};
//            default: return new char[]{};
//        }
    }

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。解集不能包含重复的组合。
     */
//    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0){
            return result;
        }
        // 因为candidates非有序数组，所以先排序
        Arrays.sort(candidates);//升序排序
        combinationSumHelper(candidates, target, 0, 0);
        return result;
    }

    private void combinationSumHelper(int[] candidates, int target, int sum, int startIndex){
        if (sum > target){
            return;
        }
        if (sum == target){
            result.add(new ArrayList<>(path));
        }
        // 如果是一个集合来求组合的话，就需要startIndex(结果去重就要加startIndex)
        // 如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++){// for循环剪枝优化
            path.add(candidates[i]);
            sum += candidates[i];
            combinationSumHelper(candidates, target, sum, i);// 不用i+1了，表示可以重复读取当前的数
            sum -= candidates[i];
            path.removeLast();
        }
    }
}
