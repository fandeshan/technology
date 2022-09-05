package keyboard_row;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 500. 键盘行
 *
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/keyboard-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/5
 */
public class Solution {
    String row1 = "qwertyuiop";
    String row2 = "asdfghjkl";
    String row3 = "zxcvbnm";
    public String[] findWords(String[] words) {
        int[] arr = new int[26];
        for (int i = 0; i < row1.length(); i++) {
            arr[row1.charAt(i)-'a'] = 1;
        }
        for (int i = 0; i < row2.length(); i++) {
            arr[row2.charAt(i)-'a'] = 2;
        }
        for (int i = 0; i < row3.length(); i++) {
            arr[row3.charAt(i)-'a'] = 3;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String lowerStr = words[i].toLowerCase();
            int sum = 0;
            for (int j = 0; j < lowerStr.length(); j++) {
                sum  = sum + arr[lowerStr.charAt(j)-'a'];
            }
            if (sum%lowerStr.length() == 0 && sum/lowerStr.length() == arr[lowerStr.charAt(0)-'a']){
                result.add(words[i]);
            }
        }
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findWords(new String[]{"Hello","Alaska","Dad","Peace"}));
    }
}
