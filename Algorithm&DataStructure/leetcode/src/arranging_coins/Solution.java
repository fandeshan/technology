package arranging_coins;

/**
 * @author fandeshan
 * @description 441. 排列硬币
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * 示例 2：
 *
 *
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arranging-coins
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/10/10
 */
public class Solution {
    public int arrangeCoins(int n) {
        int max = 1;
        for (int i = 1; i <= n; i++) {
            if (i >max){
                max = i;
            }
            n -=i;
        }
        return max;
    }

    /**
     *  考虑直接通过求解方程来计算 nn 枚硬币可形成的完整阶梯行的总行数。不妨设可以形成的行数为 xx，则有
     *
     * \frac{(x+1) \times x}{2} = n
     * 2
     * (x+1)×x
     * ​
     *  =n
     *
     * 整理得一元二次方程
     *
     * x^2 + x - 2n = 0
     * x
     * 2
     *  +x−2n=0
     *
     * 因为 n \ge 1n≥1 ，所以判别式
     *
     * \Delta = b^2 - 4ac = 8n + 1 > 0
     * Δ=b
     * 2
     *  −4ac=8n+1>0
     *
     * 解得
     *
     * x_1 = \frac{-1 - \sqrt{8n+1}}{2}, \hspace{1em} x_2 = \frac{-1 + \sqrt{8n+1}}{2}
     * x
     * 1
     * ​
     *  =
     * 2
     * −1−
     * 8n+1
     * ​
     *
     * ​
     *  ,x
     * 2
     * ​
     *  =
     * 2
     * −1+
     * 8n+1
     * ​
     *
     * ​
     *
     *
     * 因为 x_1 < 0x
     * 1
     * ​
     *  <0，故舍去。此时 x_2x
     * 2
     * ​
     *   即为硬币可以排列成的行数，可以完整排列的行数即 \lfloor x_2 \rfloor⌊x
     * 2
     * ​
     *  ⌋，其中符号 \lfloor x \rfloor⌊x⌋ 表示 xx 的向下取整。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/arranging-coins/solution/pai-lie-ying-bi-by-leetcode-solution-w52c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int arrangeCoins1(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().arrangeCoins(Integer.MAX_VALUE));
    }
}
