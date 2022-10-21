package find_all_numbers_disappeared_in_an_array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 448. 找到所有数组中消失的数字
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 *
 * @date 2022/10/21
 */
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
           if (nums[Math.abs(nums[i]) -1] > 0){
               nums[Math.abs(nums[i]) -1] = -nums[Math.abs(nums[i]) -1];
           }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                result.add(i+1);
            }
        }
        return result;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] newNum = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            newNum[nums[i]] ++;
        }
        for (int i = 1; i < newNum.length; i++) {
            if (newNum[i] == 0){
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }
}
