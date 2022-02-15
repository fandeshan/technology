package lucky_numbers_in_a_matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fandeshan
 * @description 1380. 矩阵中的幸运数
 *  给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 *
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 *
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/15
 */
public class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        Set<Integer> set = new HashSet<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int minJ = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < matrix[i][minJ]){
                    minJ = j;
                }
            }
            set.add(genKey(i,minJ));
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int maxJ = 0;
            for (int j = 1; j < m; j++) {
                if (matrix[j][i] > matrix[maxJ][i]){
                    maxJ = j;
                }
            }
            int key = genKey(maxJ,i);
            if (set.contains(key)){
                result.add(getMatrixVal(matrix,key));
            }
        }
        return result;
    }
    private int genKey(int i,int j){
        return i*100 + j;
    }

    private int getMatrixVal(int[][] matrix,int key){
        int j = key % 100;
        int i = (key - j)/100;
        return matrix[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().luckyNumbers(new int[][]{{3,7,8},{9,11,13},{15,16,17}}));
        System.out.println(new Solution().luckyNumbers(new int[][]{{1,10,4,2},{9,3,8,7},{15,16,17,12}}));
        System.out.println(new Solution().luckyNumbers(new int[][]{{7,8},{1,2}}));
    }
}
