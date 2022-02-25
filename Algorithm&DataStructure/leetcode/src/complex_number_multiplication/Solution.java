package complex_number_multiplication;

/**
 * @author fandeshan
 * @description 537. 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 *
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 *
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *  
 *
 * 提示：
 *
 * num1 和 num2 都是有效的复数表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/25
 */
public class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        String[] num1Splits = num1.split("\\+");
        int num1R= Integer.parseInt(num1Splits[0]);
        int num1I = Integer.parseInt(num1Splits[1].substring(0,num1Splits[1].length() -1));
        String[] num2Splits = num2.split("\\+");
        int num2R= Integer.parseInt(num2Splits[0]);
        int num2I = Integer.parseInt(num2Splits[1].substring(0,num2Splits[1].length() -1));
        int resultR = num1R*num2R - num1I*num2I;
        int resultI = num1R*num2I + num2R*num1I;
        return resultR +"+"+resultI+"i";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().complexNumberMultiply("1+1i","1+1i"));
        System.out.println(new Solution().complexNumberMultiply("1+-1i","1+-1i"));
    }
}
