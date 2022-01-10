package additive_number;

import java.math.BigInteger;
import java.util.Stack;

/**
 * @author fandeshan
 * @description 306. 累加数
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 *
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2：
 *
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *  
 *
 * 提示：
 *
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 *  
 *
 * 进阶：你计划如何处理由过大的整数输入导致的溢出?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/1/10
 */
public class Solution {
    public boolean isAdditiveNumber(String num) {
        Stack<BigInteger> tmp = new Stack<>();
        return dfs(num,tmp);
    }
    public boolean dfs(String num,Stack<BigInteger> tmp){
        int len = tmp.size();
        if (len >=3 && tmp.get(len-1).compareTo(tmp.get(len-2).add(tmp.get(len-3)))!=0){
            return false;
        }
        if (num.length() == 0 && len >= 3){
            return true;
        }
        for (int i = 0; i < num.length(); i++) {
            String numSplit = num.substring(0,i+1);
            if(numSplit.charAt(0)=='0' && numSplit.length()!=1)
                continue;
            tmp.push(new BigInteger(numSplit));
            if (dfs(num.substring(i+1),tmp)) return true;
            tmp.pop();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isAdditiveNumber("112358"));
        System.out.println(new Solution().isAdditiveNumber("199100199"));
        System.out.println(new Solution().isAdditiveNumber("11235813213455890144"));
    }
}
