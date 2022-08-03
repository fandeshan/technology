package orderly_queue;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 899. 有序队列
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 *
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 * 示例 2：
 *
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 *  
 *
 * 提示：
 *
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/orderly-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/3
 */
public class Solution {
    //结论：k =1 ，一个个移动字符，取最小
    // k> 1，总能移动到按照字符升序，所以直接排序字符串即可
    public String orderlyQueue(String s, int k) {
        if (k == 1){
            String minStr = s;
            StringBuffer sb = new StringBuffer(s);
            for (int i = 0; i < s.length(); i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if (minStr.compareTo(sb.toString())>0){
                    minStr = sb.toString();
                }
            }
            return minStr;
        } else {
            byte[] charArr = s.getBytes();
            Arrays.sort(charArr);
            return new String(charArr);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().orderlyQueue("cba",1));
        System.out.println(new Solution().orderlyQueue("baaca",3));
    }
}
