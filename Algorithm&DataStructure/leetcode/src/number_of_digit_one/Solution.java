package number_of_digit_one;

/**
 * @author fandeshan
 * @description 233. 数字 1 的个数
 *  给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= n <= 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/8/13
 */
public class Solution {
    public int countDigitOne(int n) {
        int res = 0;
        int digit = 1;
        int high = n/10;
        int cur = n%10;
        int low = 0;
        while (high!=0 || cur !=0){
            if (cur == 0){
                res += high * digit;
            } else if (cur == 1){
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countDigitOne(13));
    }
}
