package base_7;

/**
 * @author fandeshan
 * @description 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: num = -7
 * 输出: "-10"
 *  
 *
 * 提示：
 *
 * -107 <= num <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/7
 */
public class Solution {
    public String convertToBase7(int num) {
        if (num == 0){
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        int base = 7;
        boolean navFlag = false;
        if (num < 0){
            navFlag = true;
            num = -num;
        }
        while (num != 0){
            sb.append(num % base);
            num /=base;
        }
        if (navFlag){
            sb.append("-");
        }
        return sb.reverse().toString();
    }
    public String convertToBase71(int num) {
        return Integer.toString(num,7);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convertToBase7(100));
        System.out.println(new Solution().convertToBase7(-7));
    }
}
