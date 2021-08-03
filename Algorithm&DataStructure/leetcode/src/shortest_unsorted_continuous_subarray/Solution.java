package shortest_unsorted_continuous_subarray;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *  
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/3
 */

class Solution {
    public int findUnsortedSubarray3(int[] nums) {
        int len = nums.length;
        if(len <= 1) return 0;
        int high = 0, low = len-1, max = nums[0], min = nums[len-1];
        for(int i = 1; i < len; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[len-1-i]);
            if(nums[i] < max) high = i;
            if(nums[len-1-i] > min) low = len-1-i;
        }
        System.out.println(high+" "+low);
        return high > low ? high - low + 1 : 0;
    }
    public int findUnsortedSubarray(int[] nums) {
        int max = nums[0];
        int high = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max) {
                high = i;
            } else {
                max = nums[i];
            }


        }
        int min = nums[nums.length-1];
        int low = nums.length-1;
        for (int i = nums.length -1; i >= 0 ; i--) {
            if (nums[i] > min) {
                low = i;
            } else {
                min = nums[i];
            }


        }
        if (high <= low)
            return 0;
        return high - low + 1;
    }

    public int findUnsortedSubarray1(int[] nums) {
        int[] nums1 = Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int left;
        int right = nums.length-1;
        for (left = 0; left <nums.length ; left++) {
            if (nums[left] != nums1[left]){
                break;
            }
        }
        for (; right >= left ; right--) {
            if (nums[right] != nums1[right]){
                break;
            }
        }
        if (right< left)
            return 0;
        return right - left + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findUnsortedSubarray(new int[]{1,3,2,2,2}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{1,2,3,4}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{1}));
    }
}