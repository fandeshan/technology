package factorial_trailing_zeroes;

/**
 * @author fandeshan
 * @description 172. 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= n <= 104
 *  
 *
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/25
 */
public class Solution {

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0){
            count += n/5;
            n = n/5;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().trailingZeroes(50));
    }
}
