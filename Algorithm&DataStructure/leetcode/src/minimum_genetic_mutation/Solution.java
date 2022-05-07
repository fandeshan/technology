package minimum_genetic_mutation;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author fandeshan
 * @description 433. 最小基因变化
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 *
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 *
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 *
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * 示例 2：
 *
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * 示例 3：
 *
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 *  
 *
 * 提示：
 *
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/5/7
 */
public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        Set<String> bankSet = new HashSet<>();
        for (int i = 0; i < bank.length; i++) {
            bankSet.add(bank[i]);
        }
        if (!bankSet.contains(end)){
            return -1;
        }
        int time = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                String tmp = queue.poll();
                if (tmp.equals(end)){
                    return time;
                }
                for (int i = 0; i < bank.length; i++) {
                    int diffCnt = 0;
                    for (int j = 0; j < bank[i].length(); j++) {
                        if (bank[i].charAt(j) != tmp.charAt(j)){
                            diffCnt ++;
                        }
                    }
                    if (diffCnt == 1){
                        queue.add(bank[i]);
                    }
                }
            }

            time ++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minMutation("AACCGGTT","AACCGGTA",new String[]{"AACCGGTA"}));
        System.out.println(new Solution().minMutation("AACCGGTT","AAACGGTA",new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
        System.out.println(new Solution().minMutation("AAAAACCC","AACCCCCC",new String[]{"AAAACCCC","AAACCCCC","AACCCCCC"}));
    }
}
