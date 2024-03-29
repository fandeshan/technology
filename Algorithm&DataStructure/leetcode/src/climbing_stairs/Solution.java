package climbing_stairs;

/**
 * @author fandeshan
 * @description 70. 爬楼梯
 *
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @date 2021/7/22
 */
public class Solution {
    public int climbStairs(int n) {
        if (n == 0){
            return 0;
        }
        int[] tmp = new int[3];
        tmp[1] = 1;
        tmp[2] = 2;
        for (int i = 3; i <= n; i++) {
            tmp[i%3] = tmp[(i-1)%3] + tmp[(i-2)%3];
        }
        return tmp[n%3];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(2));
        System.out.println(new Solution().climbStairs(3));
    }
}
