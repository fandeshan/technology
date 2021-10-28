package reordered_power_of_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fandeshan
 * @description 869. 重新排序得到 2 的幂
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：true
 * 示例 2：
 *
 * 输入：10
 * 输出：false
 * 示例 3：
 *
 * 输入：16
 * 输出：true
 * 示例 4：
 *
 * 输入：24
 * 输出：false
 * 示例 5：
 *
 * 输入：46
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= N <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/10/28
 */
public class Solution {
    private List<String> results ;
    public boolean reorderedPowerOf2(int n) {
        results = new ArrayList<>();
        init();
        byte[] nBytes = Integer.toString(n).getBytes();
        Arrays.sort(nBytes);
        String targetStr = new String(nBytes);
        for (int i = 0; i < results.size(); i++) {
            if (targetStr.equals(results.get(i))){
                return true;
            }
        }
        return false;
    }
    private void init(){
        int n = 1;
        while (n<= Integer.MAX_VALUE && n >=1){
            String nStr = Integer.toString(n);
            byte[] nBytes = nStr.getBytes();
            Arrays.sort(nBytes);
            results.add(new String(nBytes));
            n = n*2;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorderedPowerOf2(46));
        System.out.println(new Solution().reorderedPowerOf2(10));
        System.out.println(new Solution().reorderedPowerOf2(1));
    }
}