package knight_probability_in_chessboard;

/**
 * @author fandeshan
 * @description 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 *
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 *
 *
 *
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 *
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 *
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 *
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *  
 *
 * 提示:
 *
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/17
 */
public class Solution {
    int[][] steps = new int[][]{{-2,-1},{-2,1},{-1,-2},{-1,2},{1,2},{1,-2},{2,-1},{2,1}};
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] memo = new double[n][n][k + 1];
        return dfs(n,k,row,column,memo);
    }
    private double dfs (int n, int k, int row, int column,double[][][] memo){
        if (k == 0){
            return 1;
        }
        if (memo[row][column][k]!=0){
            return memo[row][column][k];
        }
        double result = 0;
        for (int i = 0; i < steps.length; i++) {
            int newRow = row + steps[i][0];
            if (newRow <0 || newRow >= n){
                continue;
            }
            int newColumn = column + steps[i][1];
            if (newColumn <0 || newColumn >= n){
                continue;
            }
            result += (double) 1/8 * dfs(n,k-1,newRow,newColumn,memo);

        }
        memo[row][column][k] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().knightProbability(3,2,0,0));
        System.out.println(new Solution().knightProbability(1,0,0,0));
    }
}
