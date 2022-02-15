package single_element_in_a_sorted_array;

/**
 * @author fandeshan
 * @description 540. 有序数组中的单一元素
 *  给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/14
 */
public class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == nums[mid ^ 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        System.out.println(new Solution().singleNonDuplicate(new int[]{1,1,2,2,3,3,4,8,8}));
        System.out.println(new Solution().singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
        System.out.println(new Solution().singleNonDuplicate(new int[]{3,7,7,10,10,11,11}));
    }
}
