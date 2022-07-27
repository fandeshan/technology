package fraction_addition_and_subtraction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 592. 分数加减运算
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 *
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 *  示例 2:
 *
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 *
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 *  
 *
 * 提示:
 *
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/27
 */
public class Solution {
    public String fractionAddition(String expression) {
        if (expression == null || expression == ""){
            return expression;
        }
        List<String[]> nums = new ArrayList<>();
        List<Character> opr = new ArrayList<>();
        if (expression.charAt(0)=='-'){
            expression = "0/1"+expression;
        }
        int index = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' || expression.charAt(i) == '+'){
                nums.add(expression.substring(index,i).split("/"));
                opr.add(expression.charAt(i));
                index= i+1;
            }
        }
        nums.add(expression.substring(index).split("/"));
        if (nums.size() == 1){
            return expression;
        }
        int resultInt2 = 5040; //分母
        List<Integer> numInts = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            //5040是1-10的公约数
            numInts.add(resultInt2/Integer.parseInt(nums.get(i)[1]) *Integer.parseInt(nums.get(i)[0]));
        }
        int resultInt = 0;
        if (opr.get(0) == '-'){
            resultInt = numInts.get(0) - numInts.get(1);
        } else {
            resultInt = numInts.get(0) + numInts.get(1);
        }
        for (int i = 1; i < opr.size(); i++) {
            if (opr.get(i) == '-'){
                resultInt = resultInt - numInts.get(i+1);
            } else {
                resultInt = resultInt + numInts.get(i+1);
            }
        }
        for (int i = 10; i >1; i--) {
            if (resultInt%i == 0 && resultInt2%i == 0){
                resultInt = resultInt/i;
                resultInt2 = resultInt2/i;
                i++;
            }
        }
        return resultInt +"/"+resultInt2;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fractionAddition("-1/2+1/2"));
        System.out.println(new Solution().fractionAddition("-1/2+1/2+1/3"));
        System.out.println(new Solution().fractionAddition("1/3-1/2"));
    }
}
