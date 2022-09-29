package string_rotation_lcci;

/**
 * @author fandeshan
 * @description 面试题 01.09. 字符串轮转
 *
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * 示例1:
 *
 *  输入：s1 = "waterbottle", s2 = "erbottlewat"
 *  输出：True
 * 示例2:
 *
 *  输入：s1 = "aa", s2 = "aba"
 *  输出：False
 * 提示：
 *
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 *
 * 你能只调用一次检查子串的方法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/string-rotation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/29
 */
public class Solution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length()!=s2.length()){
            return false;
        }
        String ns2 = s2 + s2;
        return ns2.contains(s1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isFlipedString("waterbottle","erbottlewat"));
    }
}
