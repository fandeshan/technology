package XltzEq;

/**
 * @author fandeshan
 * @description 剑指 Offer II 018. 有效的回文
 *
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 本题中，将空字符串定义为有效的 回文串 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 *
 *
 * 注意：本题与主站 125 题相同： https://leetcode-cn.com/problems/valid-palindrome/
 *
 * @date 2022/11/1
 */
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toUpperCase();
        int left = 0;
        int right = s.length() - 1;
        while (left<right){
            while (left < right && (!checkChar(s.charAt(left)))){
                left ++;
            }
            while (left < right && (!checkChar(s.charAt(right)))){
                right -- ;
            }
            if (left < right ){
                if (s.charAt(left) != s.charAt(right)){
                    return false;
                }
                left ++;
                right --;
            }
        }
        return true;
    }
    private boolean checkChar(char a){
        if ((a<='Z'&& a>='A')|| (a>='0' && a<='9')){
            return true;
        }
        return false;
    }
}
