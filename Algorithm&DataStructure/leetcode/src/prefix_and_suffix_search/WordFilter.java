package prefix_and_suffix_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description 745. 前缀和后缀搜索
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 *
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 *  
 * 提示：
 *
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 104 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/14
 */
public class WordFilter {
    Map<String,Integer> wordDict;
    public WordFilter(String[] words) {
        wordDict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 1; j <= word.length(); j++) {
                for (int k = 1; k <= word.length(); k++) {
                    wordDict.put(word.substring(0,j)+","+word.substring(word.length()-k),i);
                }
            }
        }
    }

    public int f(String pref, String suff) {
        String key = pref+","+suff;
        if (!wordDict.containsKey(key)){
            return -1;
        }
        return wordDict.get(key);
    }

    public static void main(String[] args) {
        WordFilter wordFilter = new WordFilter(new String[]{"apple"});

        System.out.println(wordFilter.f("a","e"));
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
