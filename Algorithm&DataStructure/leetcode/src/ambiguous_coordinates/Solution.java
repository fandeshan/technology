package ambiguous_coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 816. 模糊坐标
 *
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 *
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 *
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 *
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 *
 *
 * 提示:
 *
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 *
 * @date 2022/11/7
 */
public class Solution {
    public List<String> ambiguousCoordinates(String s) {
        s = s.substring(1,s.length()-1);
        List<String> result = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            List<String> xs = genCoordinate(s.substring(0,i));
            List<String> ys = genCoordinate(s.substring(i));
            for (int j = 0; j < xs.size(); j++) {
                for (int k = 0; k < ys.size(); k++) {
                    result.add("("+xs.get(j)+", "+ys.get(k)+")");
                }
            }
        }
        return result;
    }
    private List<String> genCoordinate(String str){
        List<String> coordinates = new ArrayList<>();
        if (String.valueOf(Integer.parseInt(str)).equals(str)){
            coordinates.add(str);
        }
        if (str.length() > 1 && str.endsWith("0")){
            return coordinates;
        }
        for (int i = 1; i < str.length() ; i++) {
            if (str.substring(0,i).startsWith("0")&&str.substring(0,i).length() > 1){
                continue;
            }

            coordinates.add(str.substring(0,i)+"."+str.substring(i));

        }
        return coordinates;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().ambiguousCoordinates("(100011)"));
        //System.out.println(new Solution().ambiguousCoordinates("(1000100)"));
//        System.out.println(new Solution().ambiguousCoordinates("(0000000001)"));
//        System.out.println(new Solution().ambiguousCoordinates("(123)"));
//        System.out.println(new Solution().ambiguousCoordinates("(00011)"));
//        System.out.println(new Solution().ambiguousCoordinates("(0123)"));
//        System.out.println(new Solution().ambiguousCoordinates("(1000)"));
    }
}
