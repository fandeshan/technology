package self_dividing_numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 728. 自除数
 * 自除数 是指可以被它包含的每一位数整除的数。
 *
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 *
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 示例 2:
 *
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 *  
 *
 * 提示：
 *
 * 1 <= left <= right <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-dividing-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/31
 */
public class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int tmp = i;
            boolean isSDN = true;
            while (tmp > 0){
                int mod = tmp%10;
                if(mod == 0||i%(mod) !=0){
                    isSDN = false;
                    break;
                }
                tmp = tmp/10;
            }
            if (isSDN){
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().selfDividingNumbers(1,22));
        System.out.println(new Solution().selfDividingNumbers(47,85));
    }
}
