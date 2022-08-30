package search_insert_position;

/**
 * @author fandeshan
 * @description 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/30
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            int mid =start + ( end - start )/2;
            if (mid == 0){
                break;
            }
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > target && nums[mid-1] < target){
                return mid;
            }
            if (nums[mid] < target && nums[mid+1] > target){
                return mid +1;
            }
            if (nums[mid] < target){
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        if (target <= nums[start]){
            return start;
        }else if (target > nums[end]){
            return end + 1;
        } else {
            return start + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().searchInsert(new int[]{1,3},2));
        System.out.println(new Solution().searchInsert(new int[]{1,3},4));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},5));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},2));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},7));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},0));
    }
}
