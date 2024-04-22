package SkillCategory.Greedy;

public class Medium45_JumpGameII {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        //记录跳跃的次数
        int count = 0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大的覆盖区域
        int maxDistance = 0;
        for (int i = 0; i < nums.length - 1; i++) { // 注意这里是小于nums.size() - 1，这是关键所在
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i == curDistance) {
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int currentCover = 0;
        int count = 0;
        int maxCover = 0;
        for (int i = 0; i < n; i++) {
            maxCover = Math.max(nums[i] + i, maxCover);
            if (i == currentCover) {
                currentCover = maxCover;
                count++;
                if (currentCover >= n - 1) {
                    break;
                }
            }
        }
        return count;

    }
}
