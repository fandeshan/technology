package maximum_swap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fandeshan
 * @description 670. 最大交换
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 *
 * 给定数字的范围是 [0, 108]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/13
 */
public class Solution {
    public int maximumSwap(int num) {
        if (num == 0){
            return num;
        }
        List<Integer> arr =new ArrayList<>();
        while (num!=0){
            arr.add(num%10);
            num = num/10;
        }
        Collections.reverse(arr);
        List<Integer> sortArr =new ArrayList<>(arr.size());
        sortArr.addAll(arr);
        Collections.sort(sortArr,(a,b)->b.compareTo(a));
        for (int i = 0; i < arr.size(); i++) {
            if (sortArr.get(i)!=arr.get(i)){
                int lastIndex = 0;
                for (int j = i+1; j < arr.size(); j++) {
                    if (arr.get(j) == sortArr.get(i)){
                        lastIndex = j;
                    }
                }
                arr.set(lastIndex,arr.get(i));
                arr.set(i,sortArr.get(i));
                break;
            }
        }
        int result = 0;
        for (int i = 0; i < arr.size(); i++) {
            result = result*10+arr.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximumSwap(9937));
        System.out.println(new Solution().maximumSwap(993777));
    }
}
