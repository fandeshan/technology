package reverse_vowels_of_a_string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandeshan
 * @description 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 *
 * 输入："leetcode"
 * 输出："leotcede"
 *  
 *
 * 提示：
 *
 * 元音字母不包含字母 "y" 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/19
 */
public class Solution {
    Set<Character> set = new HashSet<>(5);
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        set.add('a');set.add('A');
        set.add('e');set.add('E');
        set.add('i');set.add('I');
        set.add('o');set.add('O');
        set.add('u');set.add('U');
        byte[] bytes = s.getBytes();
        int left = 0;
        int right = bytes.length - 1;

        while (left < right){
            while (left < bytes.length && (!set.contains((char)bytes[left]))){
                left ++;
            }
            while (right >= 0  && (!set.contains((char)bytes[right]))){
                right --;
            }
            if (left < right){
                byte a = bytes[right];
                bytes[right] = bytes[left];
                bytes[left] = a;
                left ++;
                right --;
            }
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseVowels("hello"));
        System.out.println(new Solution().reverseVowels("leetcode"));
    }
}