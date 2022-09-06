package max_consecutive_ones;

/**
 * @author fandeshan
 * @description 485. 最大连续 1 的个数
 *
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 示例 2:
 *
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-consecutive-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/6
 */
public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int start =0;
        int end = 0;
        int maxOne = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=1){
                start = i;
                end = i;
            } else {
                end ++;
                maxOne = Math.max(maxOne,end-start);
            }
        }
        return maxOne;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
    }
}
