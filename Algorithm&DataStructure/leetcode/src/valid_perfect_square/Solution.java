package valid_perfect_square;

/**
 * @author fandeshan
 * @description 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= num <= 2^31 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/4
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        int low = 1;
        int high = num;
        while (low <= high) {
            int mid = low + ( high - low )/2;
            int tmp = num / mid;
            if (tmp > mid){
                low = mid + 1;
            } else if (tmp  < mid){
                high = mid -1;
            } else {
                if (tmp*mid== num){
                    return true;
                }
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPerfectSquare(5));
        System.out.println(new Solution().isPerfectSquare(2147483647));
        System.out.println(new Solution().isPerfectSquare(16));
        System.out.println(new Solution().isPerfectSquare(14));
    }
}
