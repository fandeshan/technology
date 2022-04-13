package ugly_number;

/**
 * @author fandeshan
 * @description 263. 丑数
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 *
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。
 * 示例 3：
 *
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *  
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/4/8
 */
public class Solution {
    public boolean isUgly(int n) {
        if (n < 1){
            return false;
        }
        if (n == 1){
            return true;
        }
        if (n%2 == 0){
            return isUgly(n/2);
        }
        if (n%3 == 0){
            return isUgly(n/3);
        }
        if (n%5 == 0){
            return isUgly(n/5);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isUgly(6));
        System.out.println(new Solution().isUgly(12));
        System.out.println(new Solution().isUgly(12));
    }
}