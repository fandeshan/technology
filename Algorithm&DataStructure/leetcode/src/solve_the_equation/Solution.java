package solve_the_equation;

import java.util.List;

/**
 * @author fandeshan
 * @description 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 *
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 *
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 *
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 *
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *  
 *
 * 提示:
 *
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/solve-the-equation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/10
 */
public class Solution {
    public String solveEquation(String equation) {
        String[] eqs = equation.split("=");
        int xLeft,numLeft,xRight,numRight;
        int[] leftArr = splitEqs(eqs[0]);
        int[] rightArr = splitEqs(eqs[1]);
        xLeft = leftArr[0];
        numLeft = leftArr[1];
        xRight = rightArr[0];
        numRight = rightArr[1];
        int newXNum = xLeft - xRight;
        int newConNum = numRight - numLeft;
        if (newXNum == 0){
            if (newConNum == 0){
                return "Infinite solutions";
            }
            return "No solution";
        }
        return "x="+(newConNum/newXNum);
    }
    private int[] splitEqs(String eq){
        int index = 0;
        // true 为 + ，false 为 -
        boolean opr = true;
        int xNum = 0;
        int conNum = 0;
        for (int i = 0; i < eq.length(); i++) {
            if (eq.charAt(i) =='+'||eq.charAt(i) =='-'){
                String numTmp = eq.substring(index,i);
                if ("".equals(numTmp)) {
                    index = i+1;
                    opr = eq.charAt(i) =='+';
                    continue;
                }
                if (numTmp.contains("x")){
                    int currXNum;
                    if (numTmp.length() == 1){
                        currXNum = 1;
                    } else {
                        currXNum = Integer.parseInt(numTmp.substring(0,numTmp.length()-1));
                    }
                    if (opr){
                        xNum = xNum + currXNum;
                    } else {
                        xNum = xNum - currXNum;
                    }
                } else {
                    if (opr){
                        conNum = conNum + Integer.parseInt(numTmp);
                    } else {
                        conNum = conNum - Integer.parseInt(numTmp);
                    }

                }
                opr = eq.charAt(i) =='+';
                index = i+1;
            }

        }
        String numTmp = eq.substring(index);
        if ("".equals(numTmp)) {
            return new int[]{xNum,conNum};
        }
        if (numTmp.contains("x")){
            int currXNum;
            if (numTmp.length() == 1){
                currXNum = 1;
            } else {
                currXNum = Integer.parseInt(numTmp.substring(0,numTmp.length()-1));
            }
            if (opr){
                xNum = xNum + currXNum;
            } else {
                xNum = xNum - currXNum;
            }
        } else {
            if (opr){
                conNum = conNum + Integer.parseInt(numTmp);
            } else {
                conNum = conNum - Integer.parseInt(numTmp);
            }

        }
        return new int[]{xNum,conNum};
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solveEquation("-x=-1"));
        System.out.println(new Solution().solveEquation("x+5-3+x=6+x-2"));
        System.out.println(new Solution().solveEquation("x=x"));
        System.out.println(new Solution().solveEquation("2x=x"));
        System.out.println(new Solution().solveEquation("x+1=x-1"));
    }
}
