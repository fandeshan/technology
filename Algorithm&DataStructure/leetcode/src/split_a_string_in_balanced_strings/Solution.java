package split_a_string_in_balanced_strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author fandeshan
 * @description 1221. 分割平衡字符串
 * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
 *
 * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 *
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 *
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 * 示例 2：
 *
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 * 示例 3：
 *
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR".
 * 示例 4：
 *
 * 输入：s = "RLRRRLLRLL"
 * 输出：2
 * 解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s[i] = 'L' 或 'R'
 * s 是一个 平衡 字符串
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/9/7
 */
public class Solution {
    Map<Character,Integer> map = new HashMap(){{
        put('R',1);
        put('L',-1);
    }};
    public int balancedStringSplit(String s) {
        int sum = map.get(s.charAt(0));
        int cnt = 0;
        for (int i = 1; i < s.length(); i++) {
            sum += map.get(s.charAt(i));
            if (sum == 0){
                cnt ++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().balancedStringSplit("RLRRLLRLRL"));
        System.out.println(new Solution().balancedStringSplit("RLLLLRRRLR"));
        System.out.println(new Solution().balancedStringSplit("LLLLRRRR"));
        System.out.println(new Solution().balancedStringSplit("RLRRRLLRLL"));
    }
}
