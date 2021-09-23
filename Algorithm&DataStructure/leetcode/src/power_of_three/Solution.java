package power_of_three;

/**
 * @author fandeshan
 * @description 326. 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：false
 * 示例 3：
 *
 * 输入：n = 9
 * 输出：true
 * 示例 4：
 *
 * 输入：n = 45
 * 输出：false
 *  
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *  
 *
 * 进阶：
 *
 * 你能不使用循环或者递归来完成本题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/9/23
 */
public class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
    public boolean isPowerOfThree1(int n) {
        if (n == 0){
            return false;
        }
        if (n == 1){
            return true;
        }
        if (n%3 !=0){
            return false;
        }
        return isPowerOfThree(n/3);
    }


    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfThree(1));
        System.out.println(new Solution().isPowerOfThree(45));
        System.out.println(new Solution().isPowerOfThree(0));
        System.out.println(new Solution().isPowerOfThree(9));
    }
}
