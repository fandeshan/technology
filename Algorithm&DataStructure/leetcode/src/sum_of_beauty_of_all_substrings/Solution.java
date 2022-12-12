package sum_of_beauty_of_all_substrings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description 1781. 所有子字符串美丽值之和
 *
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 * 示例 2：
 *
 * 输入：s = "aabcbaa"
 * 输出：17
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * @date 2022/12/12
 */
public class Solution {
    public int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                cnt[s.charAt(j) - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[s.charAt(j) - 'a']);
                int minFreq = s.length();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minFreq = Math.min(minFreq, cnt[k]);
                    }
                }
                res += maxFreq - minFreq;
            }
        }
        return res;
    }
    public int beautySum1(String s) {
        Map<Integer,Integer> resultMemo = new HashMap<>();
        int[] countChar = new int[26];
        for (int i = 0; i < s.length(); i++) {
            countChar[s.charAt(i)-'a'] ++;
        }
        int num =  countBeautySum(s,0,s.length() - 1,resultMemo,countChar);
        return num;
    }

    private int countBeautySum(String s,int start,int end,Map<Integer,Integer> resultMemo,int[] countChar) {
        if(resultMemo.containsKey(start*1000 + end)){
            return 0;
        }
        if (start + 1 > end){
            return 0;
        }
        int max = 0,min = Integer.MAX_VALUE;
        for (int i = 0; i < countChar.length; i++) {
            if (countChar[i] > 0){
                max = Math.max(max,countChar[i]);
                min = Math.min(min,countChar[i]);
            }
        }
        if (max <= min){
            return 0;
        }
        countChar[s.charAt(start)-'a'] --;
        int countBeautySumSub1 = countBeautySum(s,start + 1,end,resultMemo,countChar);
        countChar[s.charAt(start)-'a'] ++;
        countChar[s.charAt(end)-'a'] --;
        int countBeautySumSub2 = countBeautySum(s,start,end - 1,resultMemo,countChar);
        countChar[s.charAt(end)-'a'] ++;
        int countBeautySum = max-min
                + countBeautySumSub1
                + countBeautySumSub2;
        resultMemo.put(start*1000+end,countBeautySum);
        return countBeautySum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().beautySum("woqrqcvfdgkrafoqdktsfpeygawfpdlvaylgpxhufpvucmmztjoqmxhegdpeczbtvwrmnwrvlptscwwqbjstanyqbgoagxopvgtlyzsemgktcgciualltsquepotmtszbmejbwbtzlavpxnujdsdyrypfcfcfwdidglybzvzuznytwndidzumoekzuukxtpouudsfcohapfcjjmqwjgcvalzarugmzucheydwsncxgyojnfvgroihfckmbtqewxhuqihplprgyeaqhocivaupdfokwpliziwcmuxnebxeszxbsrmffwwdz"));
        System.out.println(new Solution().beautySum("aabcb"));
        System.out.println(new Solution().beautySum("aabcbaa"));
    }
}
