package reverse_only_letters;

/**
 * @author fandeshan
 * @description 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 *
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 *
 * 提示
 *
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/23
 */
public class Solution {
    public String reverseOnlyLetters(String s) {
        if (s.length() < 2){
            return s;
        }
        byte[] bs = s.getBytes();
        int left = 0;
        int right = bs.length - 1;
        while (left <= right){
            while (left < right && (!IsLetter(bs[left]))){
                left ++;
            }
            while (left < right && (!IsLetter(bs[right]))){
                right --;
            }
            if (left < right){
                byte temp = bs[left];
                bs[left] = bs[right];
                bs[right] = temp;
            }
            left ++;
            right --;
        }
        return new String(bs);
    }
    private boolean IsLetter(byte a){
        if ((a >='a' && a <='z')||(a>='A'&&a<='Z')){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseOnlyLetters("ab-cd"));
        System.out.println(new Solution().reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(new Solution().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }
}
