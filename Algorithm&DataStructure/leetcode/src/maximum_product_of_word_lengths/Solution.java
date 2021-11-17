package maximum_product_of_word_lengths;

/**
 * @author fandeshan
 * @description 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 *
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *  
 *
 * 提示：
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/17
 */
public class Solution {
    public int maxProduct(String[] words) {
        quickSort(words,0,words.length-1);
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        System.out.println("=====================================");
        int[] arr = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int tmp = 0;
            for (int j = 0; j < words[i].length(); j++) {
                tmp = tmp | (1<<(words[i].charAt(j)-'a'));
            }
            arr[i] = tmp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(words[i]);
            System.out.println(Integer.toBinaryString(arr[i]));
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {

            for (int j = i+1; j < arr.length ; j++) {
                if ((arr[i]&arr[j]) == 0){
                    max = Math.max(words[i].length()*words[j].length(),max);

                }
            }
        }
        return max;
    }
    private void quickSort(String[] words,int start,int end){
        if (start >= end ){
            return ;
        }
        int left = start;
        int right = end;
        String base = words[left];
        while ( left <= right ) {
            while (left <= right && words[left].length() > base.length()){
                left ++;
            }
            while (left <= right && words[right].length() < base.length()){
                right --;
            }
            if (left <= right) {
                String tmp = words[left];
                words[left] = words[right];
                words[right] = tmp;
                left ++;
                right --;
            }
        }
        quickSort(words,start,right);
        quickSort(words,left,end);
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
        System.out.println(new Solution().maxProduct(new String[]{"a","ab","abc","abcd","abcde","abcdef","abcdefg","abcdefgh","abcdefghi","abcdefghij","abcdefghijk","abcdefghijkl","abcdefghijklm","nopqrstuvwxyz","mnopqrstuvwxyz","lmnopqrstuvwxyz","klmnopqrstuvwxyz","jklmnopqrstuvwxyz","ijklmnopqrstuvwxyz","hijklmnopqrstuvwxyz","ghijklmnopqrstuvwxyz","fghijklmnopqrstuvwxyz","efghijklmnopqrstuvwxyz","defghijklmnopqrstuvwxyz","cdefghijklmnopqrstuvwxyz","bcdefghijklmnopqrstuvwxyz"}));
    }
}
