package arithmetic_slices;

/**
 * @author fandeshan
 * @description 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/10
 */
public class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int start = 0;
        int end = 1;
        int ret =0;
        int sub = nums[end] - nums[start];
        while (end < nums.length ){
            while (end < nums.length -1 && nums[end] - nums[end-1] == sub){
                end ++;
            }
            if (nums[end] - nums[end-1] == sub){
                end ++;
                break;
            }
            if (end - start > 2){
                ret += nsum(end - start - 2);
            }
            start = end -1;
            sub = nums[end] - nums[start];
            end ++;
        }
        if (end - start > 2){
            ret += nsum(end - start - 2);
        }
        return ret;
    }
    private int nsum(int n){
        if (n < 1){
            return 0;
        }
        int sum = n;
        for (int i = n-1; i > 0 ; i--) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numberOfArithmeticSlices(new int[]{2,1,3,4,2,3}));
        System.out.println(new Solution().numberOfArithmeticSlices(new int[]{1,2,3,8,9,10}));
        System.out.println(new Solution().numberOfArithmeticSlices(new int[]{1,2,3,4,5,6}));
        System.out.println(new Solution().numberOfArithmeticSlices(new int[]{1,2,3,4}));
    }
}
