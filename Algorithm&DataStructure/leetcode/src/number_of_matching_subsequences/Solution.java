package number_of_matching_subsequences;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author fandeshan
 * @description 792. 匹配子序列的单词数
 *
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 *
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 *
 * @date 2022/11/17
 */
public class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character,TreeSet<Integer>> smap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            TreeSet<Integer> set = smap.getOrDefault(s.charAt(i), new TreeSet<>());
            set.add(i);
            smap.put(s.charAt(i),set);
        }
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            int index = 0;
            int j = 0;
            for (; j < words[i].length(); j++) {
                if (smap.containsKey(words[i].charAt(j))){
                    if (smap.get(words[i].charAt(j)).ceiling(index)!=null){
                        index = smap.get(words[i].charAt(j)).ceiling(index) +1;
                    } else {
                        break;
                    }
                } else{
                    break;
                }
            }
            if (j == words[i].length()){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numMatchingSubseq("abcde",new String[]{"a","bb","acd","ace"}));
    }
}
