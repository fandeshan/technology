package buddy_strings;

/**
 * @author fandeshan
 * @description 859. 亲密字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 *
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 *
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * 示例 2：
 *
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * 示例 3：
 *
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * 示例 4：
 *
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length, goal.length <= 2 * 104
 * s 和 goal 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/buddy-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/23
 */
public class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() <2 || goal.length() <2){
            return false;
        }
        if (s.length() != goal.length()){
            return false;
        }
        int diffIndex1 = -1;
        int diffIndex2 = -1;
        int cnt[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)){
                if (diffIndex1 == -1){
                    diffIndex1 = i;
                } else if(diffIndex2 == -1){
                    diffIndex2 = i;
                } else {
                    return false;
                }
            }
            cnt[s.charAt(i)-'a'] ++;
        }
        if (diffIndex1 != -1 && diffIndex2 !=-1){
            if (s.charAt(diffIndex1) == goal.charAt(diffIndex2) && s.charAt(diffIndex2) == goal.charAt(diffIndex1) ){
                return true;
            } else {
                return false;
            }
        } else if (diffIndex1 == -1 && diffIndex2 == -1){
            for (int i = 0; i < cnt.length; i++) {
                if (cnt[i] > 1){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().buddyStrings("ab","ba"));
        System.out.println(new Solution().buddyStrings("aa","aa"));
        System.out.println(new Solution().buddyStrings("ab","ab"));
        System.out.println(new Solution().buddyStrings("aaaaaaabc","aaaaaaacb"));
    }
}
