package shortest_subarray_with_sum_at_least_k;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fandeshan
 * @description 862. 和至少为 K 的最短子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 *
 * @date 2022/10/26
 */
public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque();
        for (int i = 0; i <= nums.length; i++) {
            long cur = sum[i];
            while (deque.size() > 0 && cur - sum[deque.getLast()]>=k){
                min = Math.min(min,i - deque.removeLast());
            }
            while (deque.size() > 0 && sum[deque.peek()] >= cur){
                deque.pop();
            }
            deque.push(i);
        }
        /*for (int i = 0; i < sum.length; i++) {
            for (int j = i +1; j < sum.length; j++) {
                if (sum[j] - sum[i] >= k){
                    min = Math.min(min,j-i);
                }
            }
        }*/
        return min == Integer.MAX_VALUE ? -1:min;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestSubarray(new int[]{2,-1,2},3));
    }
}
