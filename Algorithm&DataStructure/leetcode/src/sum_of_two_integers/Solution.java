package sum_of_two_integers;

/**
 * @author fandeshan
 * @description 371. 两整数之和
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 2
 * 输出：3
 * 示例 2：
 *
 * 输入：a = 2, b = 3
 * 输出：5
 *  
 *
 * 提示：
 *
 * -1000 <= a, b <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/9/24
 */
public class Solution {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getSum(a^b,(a&b)<<1);
    }
}
