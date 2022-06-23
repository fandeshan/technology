package substring_with_concatenation_of_all_words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fandeshan
 * @description 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/6/23
 */
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int swlen = words[0].length();
        int wlen = words.length;
        List<Integer> result = new ArrayList<>();
        Map<String,Integer> wordsMap = new HashMap<>(8192);
        for (int i = 0; i < s.length()-swlen*wlen+1; i++) {

            int j = i + swlen*wlen;
            for (int k = 0; k < wlen; k++) {
                wordsMap.put(words[k],wordsMap.getOrDefault(words[k],0)+1);
            }
            for (int k = i; k < j; k+=swlen) {
                String singleWord = s.substring(k,k+swlen);
                if (wordsMap.containsKey(singleWord)){
                    wordsMap.put(singleWord,wordsMap.get(singleWord)-1);
                }
            }
            boolean allContain = true;
            for (Map.Entry<String,Integer> entry : wordsMap.entrySet()) {
                if (entry.getValue()!=0){
                    allContain = false;
                }
                wordsMap.put(entry.getKey(),0);

            }
            if (allContain){
                result.add(i);
            }

        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","good"}));
        System.out.println(new Solution().findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","word"}));
        System.out.println(new Solution().findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));
        System.out.println(new Solution().findSubstring("barfoothefoobarman",new String[]{"foo","bar"}));
    }
}
