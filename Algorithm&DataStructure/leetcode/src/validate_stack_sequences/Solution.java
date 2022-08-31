package validate_stack_sequences;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author fandeshan
 * @description 946. 验证栈序列
 *
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/8/31
 */
public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Map<Integer,Integer> numsMap = new HashMap<>();
        for (int i = 0; i < pushed.length; i++) {
            numsMap.put(pushed[i],i);
        }
        Stack<Integer> stack = new Stack<>();
        int lastPushIndex = -1;
        for (int i = 0; i < popped.length; i++) {
            int j = numsMap.get(popped[i]);
            for (int k = lastPushIndex + 1; k <=j; k++) {
                stack.push(pushed[k]);
                lastPushIndex ++;
            }
            if (stack.isEmpty() || stack.pop() != popped[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validateStackSequences(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
        System.out.println(new Solution().validateStackSequences(new int[]{1,2,3,4,5},new int[]{4,3,5,1,2}));
    }
}
