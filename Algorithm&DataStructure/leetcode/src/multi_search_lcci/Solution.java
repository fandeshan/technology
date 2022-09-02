package multi_search_lcci;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 面试题 17.17. 多次搜索
 * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 *
 * 示例：
 *
 * 输入：
 * big = "mississippi"
 * smalls = ["is","ppi","hi","sis","i","ssippi"]
 * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
 * 提示：
 *
 * 0 <= len(big) <= 1000
 * 0 <= len(smalls[i]) <= 1000
 * smalls的总字符数不会超过 100000。
 * 你可以认为smalls中没有重复字符串。
 * 所有出现的字符均为英文小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multi-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/9/2
 */
public class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        int[][] result = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> posList = new ArrayList<>();
            for (int j = 0; j < big.length(); j++) {
                int r = smalls[i].length()+j;
                    if (r==j || r > big.length()){
                    break;
                }
                if (smalls[i].equals(big.substring(j,r))){
                    posList.add(j);
                }
            }
            int[] posArr = new int[posList.size()];
            for (int j = 0; j < posList.size(); j++) {
                posArr[j] = posList.get(j);
            }
            result[i] = posArr;
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().multiSearch("mississippi",new String[]{"is","ppi","hi","sis","i","ssippi"}));
    }
}
