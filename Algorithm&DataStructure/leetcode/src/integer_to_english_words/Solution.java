package integer_to_english_words;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description 273. 整数转换英文表示
 * 将非负整数 num 转换为其对应的英文表示。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 *
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 *
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4：
 *
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *  
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/10/11
 */
public class Solution {
    private static Map<Integer,String> map = new HashMap<>();
    static {
        map.put(1 ,"One");
        map.put(2 ,"Two");
        map.put(3 ,"Three");
        map.put(4 ,"Four");
        map.put(5 ,"Five");
        map.put(6 ,"Six");
        map.put(7 ,"Seven");
        map.put(8 ,"Eight");
        map.put(9 ,"Nine");
        map.put(10,"Ten");
        map.put(11,"Eleven");
        map.put(12,"Twelve");
        map.put(13,"Thirteen");
        map.put(14,"Fourteen");
        map.put(15,"Fifteen");
        map.put(16,"Sixteen");
        map.put(17,"Seventeen");
        map.put(18,"Eighteen");
        map.put(19,"Nineteen");
        map.put(20,"Twenty");
        map.put(21,"Twenty One");
        map.put(22,"Twenty Two");
        map.put(23,"Twenty Three");
        map.put(24,"Twenty Four");
        map.put(25,"Twenty Five");
        map.put(26,"Twenty Six");
        map.put(27,"Twenty Seven");
        map.put(28,"Twenty Eight");
        map.put(29,"Twenty Nine");
        map.put(30,"Thirty");
        map.put(31,"Thirty One");
        map.put(32,"Thirty Two");
        map.put(33,"Thirty Three");
        map.put(34,"Thirty Four");
        map.put(35,"Thirty Five");
        map.put(36,"Thirty Six");
        map.put(37,"Thirty Seven");
        map.put(38,"Thirty Eight");
        map.put(39,"Thirty Nine");
        map.put(40,"Forty");
        map.put(41,"Forty One");
        map.put(42,"Forty Two");
        map.put(43,"Forty Three");
        map.put(44,"Forty Four");
        map.put(45,"Forty Five");
        map.put(46,"Forty Six");
        map.put(47,"Forty Seven");
        map.put(48,"Forty Eight");
        map.put(49,"Forty Nine");
        map.put(50,"Fifty");
        map.put(51,"Fifty One");
        map.put(52,"Fifty Two");
        map.put(53,"Fifty Three");
        map.put(54,"Fifty Four");
        map.put(55,"Fifty Five");
        map.put(56,"Fifty Six");
        map.put(57,"Fifty Seven");
        map.put(58,"Fifty Eight");
        map.put(59,"Fifty Nine");
        map.put(60,"Sixty");
        map.put(61,"Sixty One");
        map.put(62,"Sixty Two");
        map.put(63,"Sixty Three");
        map.put(64,"Sixty Four");
        map.put(65,"Sixty Five");
        map.put(66,"Sixty Six");
        map.put(67,"Sixty Seven");
        map.put(68,"Sixty Eight");
        map.put(69,"Sixty Nine");
        map.put(70,"Seventy");
        map.put(71,"Seventy One");
        map.put(72,"Seventy Two");
        map.put(73,"Seventy Three");
        map.put(74,"Seventy Four");
        map.put(75,"Seventy Five");
        map.put(76,"Seventy Six");
        map.put(77,"Seventy Seven");
        map.put(78,"Seventy Eight");
        map.put(79,"Seventy Nine");
        map.put(80,"Eighty");
        map.put(81,"Eighty One");
        map.put(82,"Eighty Two");
        map.put(83,"Eighty Three");
        map.put(84,"Eighty Four");
        map.put(85,"Eighty Five");
        map.put(86,"Eighty Six");
        map.put(87,"Eighty Seven");
        map.put(88,"Eighty Eight");
        map.put(89,"Eighty Nine");
        map.put(90,"Ninety");
        map.put(91,"Ninety One");
        map.put(92,"Ninety Two");
        map.put(93,"Ninety Three");
        map.put(94,"Ninety Four");
        map.put(95,"Ninety Five");
        map.put(96,"Ninety Six");
        map.put(97,"Ninety Seven");
        map.put(98,"Ninety Eight");
        map.put(99,"Ninety Nine");
        map.put(100,"Hundred");
        map.put(0,"Zero");
        map.put(-1,"Thousand");
        map.put(-2,"Million");
        map.put(-3,"Billion");
    }
    public String numberToWords(int num) {
        if (num == 0){
            return map.get(0);
        }
        String numStr = String.valueOf(num);
        int index = -1;
        String rst = "";
        for (int i = numStr.length() - 1; i >= 0; i-=3) {

            if ( i > 2){
                rst = map.get(index--)
                        + " " + appendHundred(rst,numStr,i) ;
            } else if (i == 2){
                rst = appendHundred(rst,numStr,i) ;
            } else {
                rst = appendLessHundred(rst,numStr,i);
            }

        }
        return rst.replace("Million Thousand","Million").replace("Billion Million","Billion").trim();
    }
    private int toInt(String str,int start,int end){
        return Integer.parseInt(str.substring(start,end));
    }
    private String appendHundred(String rst,String str,int i){
        if (toInt(str,i-2,i-1) == 0){
            return appendLessHundred(rst,str,i) ;
        }
        return map.get(toInt(str,i-2,i-1))
                + " " + map.get(100) + " " + appendLessHundred(rst,str,i);
    }
    private String appendLessHundred(String rst,String str,int i){
        if (toInt(str,(i-1 < 0 ? 0 : i-1),i+1) == 0){
            return rst;
        }
        return map.get(toInt(str,(i-1 < 0 ? 0 : i-1),i+1))+ " " + rst;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numberToWords(0));
        System.out.println(new Solution().numberToWords(10));
        System.out.println(new Solution().numberToWords(100));
        System.out.println(new Solution().numberToWords(1000));
        System.out.println(new Solution().numberToWords(10000));
        System.out.println(new Solution().numberToWords(100000));
        System.out.println(new Solution().numberToWords(1000000));
        System.out.println(new Solution().numberToWords(1000001));
        System.out.println(new Solution().numberToWords(123));
        System.out.println(new Solution().numberToWords(12345));
        System.out.println(new Solution().numberToWords(1234567));
        System.out.println(new Solution().numberToWords(1234567891));
    }
}
