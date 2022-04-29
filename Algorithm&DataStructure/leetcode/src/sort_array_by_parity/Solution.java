package sort_array_by_parity;

/**
 * @author fandeshan
 * @description 905. 按奇偶排序数组
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 *
 * 返回满足此条件的 任一数组 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/4/28
 */
public class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        while (left < right){
            while (left < right && nums[left]%2 == 0){
                left ++;
            }
            while (left < right && nums[right]%2!=0){
                right --;
            }
            if (left < right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortArrayByParity(new int[]{3,1,2,4}));
    }
}
