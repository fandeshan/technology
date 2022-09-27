package check_permutation_lcci;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 面试题 01.02. 判定是否互为字符重排
 *
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/27
 */
public class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0){
            return true;
        }
        if (s1.length() == 0 || s2.length() == 0){
            return false;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return new String(cs1).equals(new String(cs2));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().CheckPermutation("abc","bca"));
        System.out.println(new Solution().CheckPermutation("abc","bad"));
    }
}
