package add_digits;

/**
 * @author fandeshan
 * @description 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * 示例 1:
 *
 * 输入: num = 0
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 *  
 *
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/3
 */
public class Solution {
    public int addDigits(int num) {
        int result = 0;
        result+= num % 10;
        while (num > 9){
            num = num/10;
            result+= num % 10;
        }
        if (result > 9){
            return addDigits(result);
        }
        return result;
    }
    public int addDigits2(int num) {
        return (num-1)%9+1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addDigits(38));
        System.out.println(new Solution().addDigits(0));
        System.out.println(new Solution().addDigits(999));
    }
}