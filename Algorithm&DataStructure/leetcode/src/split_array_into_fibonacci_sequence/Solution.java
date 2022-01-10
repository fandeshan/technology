package split_array_into_fibonacci_sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 842. 将数组拆分成斐波那契序列
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 *
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 *
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 *
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 *
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 *
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 *
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 *
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/1/10
 */
public class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> tmpList = new ArrayList<>();
        return dfs(num,tmpList);

    }
    private List<Integer> dfs(String num,List<Integer> tmpList){
        int n = tmpList.size();
        if (n >= 3 && tmpList.get(n-1) != tmpList.get(n-2) + tmpList.get(n-3)){
            return new ArrayList<>();
        }
        if (n >=3 && num.length() == 0){
            return tmpList;
        }
        for (int i = 0; i < num.length(); i++) {

            String numSplit = num.substring(0,i+1);
            if(numSplit.charAt(0)=='0' && numSplit.length()!=1) continue;
            // 超过最大int，直接退出
            Integer numSplitInt;
            try {
                numSplitInt = Integer.parseInt(numSplit);
            }catch (Exception e){
                break;
            }
            tmpList.add(numSplitInt);
            if (dfs(num.substring(i+1),tmpList).size() > 0){
                return tmpList;
            }
            tmpList.remove(n);

        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().splitIntoFibonacci("123456579"));
        System.out.println(new Solution().splitIntoFibonacci("11235813"));
        System.out.println(new Solution().splitIntoFibonacci("112358130"));
        System.out.println(new Solution().splitIntoFibonacci("0123"));
        System.out.println(new Solution().splitIntoFibonacci("1101111"));
    }
}
