package Backtracking;

import java.util.*;
import java.util.stream.Collectors;

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
            return;
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

    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 集合（数组candidates）有重复元素，但还不能有重复的组合
     * 要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重
     */
//   List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//   LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0){
            return result;
        }
        used = new boolean[candidates.length];
        Arrays.sort(candidates);
        combinationSum2Helper(candidates, target, 0, 0);
        return result;
    }

    private void combinationSum2Helper(int[] candidates, int target, int sum, int startIndex){
        if (sum == target){
            result.add(new ArrayList<>(path));
            return;
        }
        // 也可以用HashSet对同一父节点下的同一层去重
//        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++){
            // 组合问题去重
            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]){
                continue;
            }
            // 也可以用HashSet对同一父节点下的同一层去重
//            if (set.contains(candidates[i])){
//                continue;
//            }
            path.add(candidates[i]);
            sum += candidates[i];
            used[i] = true;
//            set.add(candidates[i]);
            combinationSum2Helper(candidates, target, sum, i + 1);// 和39.组合总和的区别1：这里是i+1，每个数字在每个组合中只能使用一次
            path.removeLast();
            sum -= candidates[i];
            used[i] = false;
        }
    }

    /**
     * 131. 分割回文串
     */
    List<List<String>> result131 = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<String> path131 = new LinkedList<>();// 用来存放符合条件结果
    public List<List<String>> partition(String s) {
        if (s.isEmpty()){
            return result131;
        }
        partitionHelper(s, 0);
        return result131;
    }

    private void partitionHelper(String s, int startIndex){
        if (startIndex >= s.length()){
            result131.add(new ArrayList<>(path131));
            return;
        }
        for (int i = startIndex; i < s.length(); i++){
            if (isPalindrome(s, startIndex, i)){
                // 获取[startIndex,i]在s中的子串
                String str = s.substring(startIndex, i + 1);
                path131.add(str);
            }else {
                //得到的子串不是回文串，往后尝试，寻找下一个可行位置，才能调用下一层递归
                continue;
            }
            partitionHelper(s, i + 1);
            path131.removeLast();
        }
    }

    // 判断字符串是否为回文串
    private boolean isPalindrome(String s, int start, int end){
        char[] chars = s.toCharArray();
        for (int i = start, j = end; i < j; i++, j--){
            if (chars[i] != chars[j]){
                return false;
            }
        }
        return true;
    }

    /**
     * 93. 复原 IP 地址
     */
    List<String> result93 = new LinkedList<>();// 存放符合条件结果的集合
    LinkedList<String> path93 = new LinkedList<>();// 用来存放符合条件结果
    String dot = ".";
    char[] chars;
    public List<String> restoreIpAddresses(String s) {
        //一开始小于4个数或者大于12个数，肯定是构不成合法ip地址的
        if (s.length() < 4 || s.length() > 12){
            return result93;
        }
        chars = s.toCharArray();
        restoreIpAddressesHelper(s, 0);
        return result93;
    }

    private void restoreIpAddressesHelper(String s, int startIndex){
        if (startIndex == s.length() && path93.size() == 4){
            String str = String.join(dot, path93);// 加.
            result93.add(str);
            return;
        }
        for (int i = startIndex; i < s.length(); i++){
            //判断是否合法
            if (isIPAddress(s, startIndex, i)){
                String str = s.substring(startIndex, i + 1);
                path93.add(str);
            }else {
                // 因为剩下都不合法了，就跳过这层
                break;
            }
            restoreIpAddressesHelper(s, i + 1);
            path93.removeLast();
        }
    }

    private boolean isIPAddress(String s, int start, int end){
        //不能以0开头
        if (chars[0] == '0' && start != end){
            return false;
        }
        //不能大于255
        return Integer.parseInt(s.substring(start, end)) <= 255;
    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 [互不相同] 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 「那么组合问题和分割问题都是收集树的叶子节点，而子集问题是找树的所有节点！」
     */
//    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        subsetsHelper(nums, 0);
        return result;
    }

    private void subsetsHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }

    /**
     * 90. 子集 II
     * 给定一个可能[包含重复元素]的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     *             // 同一树枝可以重复选取（可以选取重复的另一个数）
     *             // 同一树层不能重复选取（因为这样会得到重复的子集）
     */
//    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
//    boolean[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0){
            result.add(path);
            return result;
        }
        Arrays.sort(nums);// 排序 「整颗树的同一层是否重复出现元素是我们控制不了的（这颗树本来就是抽象出来的），
//     * 所以子集问题才会通过排序之后，判断相邻两个分支的同一层是否同时出现元素来达到去重的目的」。
        used = new boolean[nums.length];
        subsetsWithDupHelper(nums, 0);
        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            // 子集问题去重
            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过（在递归中）
            // 在回溯之前都是true，就是一直在同一树枝上
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 因为运行到了这行，要开始下一次遍历，就是同一树层选取另一个数了。
            used[i] = false;
        }
    }

    /**
     * 491. 递增子序列
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     * 给定数组的长度不会超过15。
      数组中的整数范围是 [-100,100]。
      给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     * 也是子集问题
     * 同一树枝需要去重
     */
    //    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
//    boolean[] used;
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        // 注意，这里没有排序
        used = new boolean[nums.length];
        findSubsequencesHelper(nums, 0, null);
        return result;
    }

    private void findSubsequencesHelper(int[] nums, int startIndex, Integer preNum){
        if (path.size() >= 2){
            result.add(new ArrayList<>(path));
            // 注意这里不要加return，要取树上的节点
        }
        if (startIndex >= nums.length){
            return;
        }
//        Set<Integer> set = new HashSet<>();// 使用set对【本层】元素进行去重，一个set一个新的for循环（树层）
        // 本层去重，是指同一个父节点下的本层，而不是整颗树的本层。（之前说的是整棵树）
        //「其实用数组来做哈希，效率就高了很多」。
        int[] set = new int[201];// 这里使用数组来进行去重操作，题目说数值范围[-100, 100]
        for (int i = startIndex; i < nums.length; i++){
//            if ((i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) || (preNum != null && nums[i] < preNum) || set.contains(nums[i])){
//                continue;
//            }
            if ((i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) || (preNum != null && nums[i] < preNum)){
                continue;
            }
            if (set[nums[i] + 100] == 1){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;// 对同一树枝去重
//            set.add(nums[i]);// 记录这个元素在本层(同一个父节点下的本层)用过了，本层后面不能再用了
            set[nums[i] + 100] = 1; // 记录这个元素在本层(同一个父节点下的本层)用过了，本层后面不能再用了
            findSubsequencesHelper(nums, i + 1, nums[i]);
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * 「整颗树的同一层是否重复出现元素是我们控制不了的（这颗树本来就是抽象出来的），
     * 所以子集问题才会通过排序之后，判断相邻两个分支的同一层是否同时出现元素来达到去重的目的」。
     *
     * 「我在归纳总结一下：」
     *     回溯算法：递增子序列的去重是同一个父节点下的本层的去重。
     *     回溯算法：求子集问题（二）的去重是要整颗树的本层去重，但是整棵树的同一层去重不好操作，所以才排序，与前一个树枝对比就可以了。
     *
     * 理解以上内容，就知道了：
     *     回溯算法：递增子序列去重用set的定义为什么放在单层搜索的逻辑里，而不是放在全局变量里。
     *     为什么回溯算法：求子集问题（二）的去重要排序。
     */

    /**
     * 46.全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 排列是有序的，也就是说[1,2] 和[2,1] 是两个集合，这和之前分析的子集以及组合所不同的地方。
     */
    //    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    //    boolean[] used;
    public List<List<Integer>> permute1(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return result;
    }

    private void permuteHelper(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        // 这里和组合问题、切割问题和子集问题最大的不同就是for循环里不用startIndex了。
        // 因为排列问题，每次都要从头开始搜索，例如元素1在[1,2]中已经使用过了，但是在[2,1]中还要再使用一次1。
        //排列问题的不同：
        //    每层都是从0开始搜索而不是startIndex
        //    需要used数组记录path里都放了哪些元素了
        for (int i = 0; i < nums.length; i++){
//            if (path.contains(nums[i])){
//                continue;
//            }
            if (used[i]){ //同一树枝，已经选取过的元素就不用再选了
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteHelper(nums);
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 「一般来说：组合问题和排列问题是在树形结构的叶子节点上收集结果，而子集问题就是取树上所有节点的结果」。
     */
//    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
//    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
//    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        //「还要强调的是去重一定要对元素进行排序，这样我们才方便通过相邻的节点来判断是否重复使用了」。
        Arrays.sort(nums);
        used = new boolean[nums.length];
        permuteUniqueHelper(nums);
        return result;
    }

    private void permuteUniqueHelper(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        // 也可以用HashSet对同一父节点下的同一层去重
//        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            // nums[i] == nums[i - 1] 前面已排过序，相同的元素一定相邻
            // !used[i - 1] 说明同一父节点下的同一树层，换树枝了
            // 两者结合，说明同一树层，与nums[i]相等的已经选取过所有可能性了
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            // 也可以用HashSet对同一父节点下的同一层去重
//            if (set.contains(nums[i])){
//                continue;
//            }
            // used[i] 说明在同一树枝上，不能重复选择，所以需要去重，避免出现元素选取重复
            if (used[i]){
                continue;
            }
            // 如果要对树层中前一位去重，就用used[i - 1] == false，如果要对树枝前一位去重用used[i - 1] == true。
            //「对于排列问题，树层上去重和树枝上去重，都是可以的，但是树层上去重效率更高！」
//            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
//                continue;
//            }
            path.add(nums[i]);
            used[i] = true;
//            set.add(nums[i]);
            permuteUniqueHelper(nums);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 332. 重新安排行程
     */
    private Map<String, PriorityQueue<String>> map = new HashMap<>();// 优先级队列

    private List<String> resList = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // 建立映射关系
        for (List<String> ticket : tickets) {
            String src = ticket.get(0); // 出发机场
            String dst = ticket.get(1); // 到达机场
            if (!map.containsKey(src)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(src, pq);
            }
            map.get(src).add(dst);
        }
        dfs("JFK");
        return resList;
    }

    private void dfs(String src) {
        PriorityQueue<String> pq = map.get(src); // 拿到出发机场对应的到达机场优先级队列
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll());// 暗藏回溯
        }
        // 从终点机场开始add，所以是addFirst
        ((LinkedList<String>) resList).addFirst(src);
    }
//    String constant = "JFK";
//    // <出发机场, map<到达机场, 航班次数>>
//    HashMap<String, HashMap<String, Integer>> targets = new HashMap<>();
//    LinkedList<String> result332 = new LinkedList<>();
//    HashMap<String, Integer> map = new HashMap<>();
//    public List<String> findItinerary(List<List<String>> tickets) {
//        for (List<String> list: tickets){
//            if (targets.containsKey(list.get(0))){
//                HashMap<String, Integer> map = targets.get(list.get(0));
//                map.put(list.get(1), map.get(list.get(1)) + 1);
//                targets.put(list.get(0), map);
//            }else {
//                HashMap<String, Integer> map = new HashMap<>();;
//                map.put(list.get(1), 0);
//                targets.put(list.get(0), map);
//            }
//        }
//        result332.add(constant);
//        findItineraryHelper(tickets.size());
//        return result332;
//    }
//
//    // 我们之前讲解回溯算法的时候，一般函数返回值都是void，这次为什么是bool呢？
//    // 因为我们只需要找到一个行程，就是在树形结构中唯一的一条通向叶子节点的路线，
//    private boolean findItineraryHelper(int ticketNum){
//        if (result332.size() == ticketNum + 1){
//            return true;
//        }
//        map = targets.get(result332.get(result332.size() - 1));
//        for (String key: map.keySet()){
//            if (map.get(key) > 0){
//                result332.add(key);
//                map.put(key, map.get(key) - 1);
//                targets.put(result332.get(result332.size() - 1), new HashMap<>(map));
//                if (findItineraryHelper(ticketNum)){
//                    return true;
//                }
//                result332.removeLast();
//                map.put(key, map.get(key) + 1);
//                targets.put(result332.get(result332.size() - 1), new HashMap<>(map));
//            }
//        }
//        return false;
//    }
    /**
     * 51. N 皇后
     */
    List<List<String>> result51 = new ArrayList<>();
    char[][] chessBoard; // 存棋子路线的数组
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0){
            return result51;
        }
        chessBoard = new char[n][n];
        solveNQueensHelper(n, 0);
        return result51;
    }

    /**
     * N皇后 Helper
     * @param n 棋牌大小
     * @param row 遍历到第几行
     */
    private void solveNQueensHelper(int n, int row){
        // 当递归到棋盘最底层（也就是叶子节点）的时候，就可以收集结果并返回了。
        if (row == n){
            result51.add(buildList(n));
            return;
        }
        // 棋盘的宽度就是for循环的长度，递归的深度就是棋盘的高度
        // 递归深度就是row控制棋盘的行，每一层里for循环的col控制棋盘的列，一行一列，确定了放置皇后的位置。
        // 每次都是要从新的一行的起始位置开始搜，所以都是从0开始。
        for (int col = 0; col < n; col++){
            if (isValidNQueen(n, row, col)){// 验证合法就可以放
                chessBoard[row][col] = 'Q';// 放置皇后
                solveNQueensHelper(n, row + 1);
                chessBoard[row][col] = '.';// 回溯，撤销皇后
            }
        }
    }

    private boolean isValidNQueen(int n, int row, int col){
        // 为什么没有在同行进行检查呢？
        // 因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。

        // 检查同列
        for (int i = 0; i < row; i++){
            if (chessBoard[i][col] == 'Q'){
                return false;
            }
        }
        // 检查 45度角是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j  >= 0; i--, j--){
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        // 检查 135度角是否有皇后
        for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    // 构建List
    private List<String> buildList(int n){
        List<String> list = new ArrayList<>();
        for (int row = 0; row < n; row++){
            int col = 0;
            StringBuilder sb = new StringBuilder();
            while (col < n){
                if (chessBoard[row][col] == 'Q'){
                    sb.append(chessBoard[row][col]);
                }else {
                    sb.append('.');
                }
                col++;
            }
            list.add(new String(sb));
        }
        return list;
    }
}
