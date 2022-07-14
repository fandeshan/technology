package prefix_and_suffix_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
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
