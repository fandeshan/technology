package partition_array_into_disjoint_intervals;

/**
 * @author fandeshan
 * @description 915. 分割数组
 *
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 *
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 *
 * 用例可以保证存在这样的划分方法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 * 示例 2：
 *
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 0 <= nums[i] <= 106
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
 *
 * @date 2022/10/24
 */
public class Solution {
    public int partitionDisjoint(int[] nums) {
        int splitIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[splitIndex]){
                int currI = i;
                int maxJ = i;
                int j = currI + 1;
                int originSplit = splitIndex;
                splitIndex = i;
                for (; j < nums.length; j++) {
                    if (nums[j] < nums[originSplit]){
                        i = j;
                        splitIndex = maxJ;
                        break;
                    }
                    if (nums[j] >= nums[maxJ]){
                        maxJ = j;
                    }
                }
                if (j == nums.length){
                    return splitIndex;
                }

            }
        }
        return splitIndex;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().partitionDisjoint(new int[]{26,51,40,58,42,76,30,48,79,91}));
        System.out.println(new Solution().partitionDisjoint(new int[]{1,5,6,3,8,7}));
        System.out.println(new Solution().partitionDisjoint(new int[]{5,0,3,8,6}));
        System.out.println(new Solution().partitionDisjoint(new int[]{1,1,1,0,6,12}));
    }
}
