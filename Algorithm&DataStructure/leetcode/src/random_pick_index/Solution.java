package random_pick_index;

import java.util.*;

/**
 * @author fandeshan
 * @description 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * 示例:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 *
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/4/25
 */
public class Solution {
    Map<Integer, List<Integer>> numsMap;
    Random random;
    public Solution(int[] nums) {
        numsMap = new HashMap<>();
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = numsMap.getOrDefault(nums[i],new ArrayList<>());
            indexList.add(i);
            numsMap.put(nums[i],indexList);
        }
    }

    public int pick(int target) {
        List<Integer> indexList = numsMap.get(target);
        return indexList.get(random.nextInt(indexList.size()));
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,3,3};
        Solution solution = new Solution(nums);
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
