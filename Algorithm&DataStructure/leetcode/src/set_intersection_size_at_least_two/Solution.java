package set_intersection_size_at_least_two;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 *
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/set-intersection-size-at-least-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/22
 */
public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // 首先排序, 按右端点来(看了标签的排序和贪心, 以及读了下评论...有点作弊了)
        Arrays.sort(intervals, (o1, o2) -> o1[1] > o2[1] ? 1 : (o1[1] == o2[1] ? (Integer.compare(o1[0], o2[0])) : -1));
        // 先选择左边的最大数
        int result = 2;
        int v1 = intervals[0][1] - 1;
        int v2 = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (v2 < intervals[i][0]) {
                // 如果前一个区间的最大值不在后一个区间内, 那么我贪心的选取后一个区间的两个最大值
                // ..v1..v2..cur1..cur2..
                v1 = intervals[i][1] - 1;
                v2 = intervals[i][1];
                result += 2;
            } else if (v1 < intervals[i][0] && intervals[i][0] <= v2) {
                // 此时继续贪心选取当前区间最大值, 同时需要加入一个值到结果中
                // ..v1..cur1..v2..cur2..
                if (v2 < intervals[i][1]) {
                    v1 = v2;
                    v2 = intervals[i][1];
                } else {
                    // 如果v2和当前区间右边界一样, 则只需要更新v1
                    v1 = intervals[i][1] - 1;
                }
                result++;
            }
            // 当v1>=intervals[i][0]时候, 此时v1和v2必定再当前区间内(排序后当前区间的右边界大于等于v2), 因此不需要选
            // ..cur1..v1..v2..cur2..  不需要担心这种情况下为什么不选后面
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().intersectionSizeTwo(new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}}));
        System.out.println(new Solution().intersectionSizeTwo(new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}}));
    }
}
