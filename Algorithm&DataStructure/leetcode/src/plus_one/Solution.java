package plus_one;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/10/21
 */
public class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        for (; i >= 0 ; i--) {
            if (digits[i] + 1 > 9){
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (i == -1){
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int j = 0; j < digits.length ; j++) {
                newDigits[j+1] = digits[j];
            }
            return newDigits;
        } else {
            return digits;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().plusOne(new int[]{1,2,3}));
        System.out.println(new Solution().plusOne(new int[]{9,9,9}));
    }
}
