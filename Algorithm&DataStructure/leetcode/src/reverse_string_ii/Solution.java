package reverse_string_ii;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 示例 2：
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/8/20
 */
public class Solution {
    public String reverseStr(String s, int k) {
        byte[] bytes = s.getBytes();
        int i = 0;
        for (; i < s.length(); i+=2*k) {
            if (i + k < s.length()){
                reverse(bytes,i,i + k-1);
            }
        }
        i = i - 2*k;
        int rest = s.length() - i;
        if (rest <= k && i >= 0){
            reverse(bytes,i,s.length()-1);
        }
        return new String(bytes);
    }
    private void reverse(byte[] bytes,int left,int right){
        while (left < right ) {
            byte tmp = bytes[left];
            bytes[left] = bytes[right];
            bytes[right] = tmp;
            left ++;
            right --;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseStr("abcd",4));
        System.out.println(new Solution().reverseStr("abcdefgh",3));
        System.out.println(new Solution().reverseStr("abcdefg",2));
        System.out.println(new Solution().reverseStr("abcd",2));
    }
}
