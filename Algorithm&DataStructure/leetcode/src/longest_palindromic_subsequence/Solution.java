package longest_palindromic_subsequence;

/**
 * @author fandeshan
 * @description 516. 最长回文子序列
 *  给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/8/12
 */
class Solution {
    public int longestPalindromeSubseq(String s) {
        int [][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindromeSubseq("bbbab"));
        System.out.println(new Solution().longestPalindromeSubseq("cbbd"));
        System.out.println(new Solution().longestPalindromeSubseq("dabbcdca"));
        System.out.println(new Solution().longestPalindromeSubseq("abcdbca"));
    }
}
