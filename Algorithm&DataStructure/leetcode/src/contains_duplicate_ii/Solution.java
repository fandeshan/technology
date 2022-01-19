package contains_duplicate_ii;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandeshan
 * @description 219. 存在重复元素 II
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *  
 *
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/1/19
 */
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1){
            return false;
        }
        Set<Integer> repeatSet = new HashSet<Integer>();
        for (int i = 0; i < k && i < nums.length; i++) {
            if (repeatSet.contains(nums[i])){
                return true;
            }
            repeatSet.add(nums[i]);
        }
        if (k >= nums.length){
            return false;
        }
        for (int i = k; i < nums.length; i++) {
            if (repeatSet.contains(nums[i])){
                return true;
            }

            repeatSet.add(nums[i]);
            repeatSet.remove(nums[i-k]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyDuplicate(new int[]{1,2,1},0));
        System.out.println(new Solution().containsNearbyDuplicate(new int[]{1,2,3,1},3));
        System.out.println(new Solution().containsNearbyDuplicate(new int[]{1,0,1,1},1));
        System.out.println(new Solution().containsNearbyDuplicate(new int[]{1,2,3,1,2,3},2));
    }
}
