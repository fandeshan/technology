package reconstruct_original_digits_from_english;

import design_add_and_search_words_data_structure.WordDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 *
 * 输入：s = "fviefuro"
 * 输出："45"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/24
 */
public class Solution {
    //Map<Character,Integer> chMaps = new HashMap(){'e','g','f','i','h','o','n','s','r','u','t','w','v','x','z'};
    String[] numEns = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};

    /**
     *  原单词 "zero","one","two","three","four","five","six","seven","eight","nine"
     *
     *   以下单词可以确定数字
     *     0 z 确定唯一
     *     1 单词 o，除 0,2,4都出现一次，其他为1出现,单词n 7，9 都有出现，还是用o吧
     *     2 w 确定唯一
     *     3 单词 t 除 2 出现一次，其他为3 出现
     *     4 u 确定唯一
     *     5 单词 f 除 4出现一次，其余为 5 出现
     *     6 x 确定唯一
     *     7 单词 s 除6 出现一次，其余为 7 出现
     *     8 g 确定唯一
     *     9 单词i 除5，6，8 出现一次，其余为 9 出现
     *
     */
    public String originalDigits(String s) {
        int[] cnts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i)-'a']++;
        }
        int cnt0 = cnts['z'-'a'];
        int cnt2 = cnts['w'-'a'];
        int cnt4 = cnts['u'-'a'];
        int cnt6 = cnts['x'-'a'];
        int cnt8 = cnts['g'-'a'];
        int cnt1 = cnts['o'-'a'] - cnt0 - cnt2 - cnt4;
        int cnt3 = cnts['t'-'a'] - cnt2;
        int cnt5 = cnts['f'-'a'] - cnt4;
        int cnt7 = cnts['s'-'a'] - cnt6;
        int cnt9 = cnts['s'-'a'] - cnt5 - cnt6 - cnt8;

        return concatStr(cnt0,cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,cnt7,cnt8,cnt9);
    }
    private String concatStr(Integer ...cnt){
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i] > 0){
                result.append(i);
                cnt[i] --;
            }
        }
        return result.toString();
    }
    public String originalDigits1(String s) {
        if (s.length() < 3){
            return "";
        }
        int[] cnts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i)-'a']++;
        }
        String result = "";
        int[][] numsCnts = initDigits();
        for (int i = 0; i < numsCnts.length; i++) {
            boolean add = true;
            for (int j = 0; j < numsCnts[i].length; j++) {
                if (numsCnts[i][j] > cnts[j]){
                    add = false;
                    break;
                }
            }
            if (add){
                for (int j = 0; j < numsCnts[i].length; j++) {
                    cnts[j] -= numsCnts[i][j];
                }
                result += i;
                i --;
            }
        }
        return result;
    }
    private int[][] initDigits(){
        int[][] result = new int[numEns.length][26];
        for (int i = 0; i < numEns.length; i++) {
            int[] tmp = new int[26];
            for (int j = 0; j < numEns[i].length(); j++) {
                tmp[numEns[i].charAt(j)-'a']++;
            }
            result[i] = tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().originalDigits("zerozerozero"));
        System.out.println(new Solution().originalDigits("owoztneoer"));
        System.out.println(new Solution().originalDigits("fviefuro"));
    }
}
