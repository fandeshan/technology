package n_repeated_element_in_size_2n_array;

/**
 * @author fandeshan
 * @description 961. 在长度 2N 的数组中找出重复 N 次的元素
 * 给你一个整数数组 nums ，该数组具有以下属性：
 *
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 104
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/5/23
 */
public class Solution {
    public int repeatedNTimes(int[] nums) {
        int i;
        if(nums[1]==nums[3]) return nums[1];
        for(i=0;i<nums.length-1;i++)
            if(nums[i]==nums[i+1])
                return nums[i];
        return nums[0];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedNTimes(new int[]{5,1,5,2,5,3,5,4}));
        System.out.println(new Solution().repeatedNTimes(new int[]{1,2,3,3}));
    }
}
