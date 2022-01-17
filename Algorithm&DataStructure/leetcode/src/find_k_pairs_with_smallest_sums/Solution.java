package find_k_pairs_with_smallest_sums;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author fandeshan
 * @description 373. 查找和最小的K对数字
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 *
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *  
 *
 * 提示:
 *
 * 1 <= nums1.length, nums2.length <= 104
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/1/14
 */
public class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap = new PriorityQueue<List<Integer>>((a,b)->{
            int sumA = a.get(0) + a.get(1);
            int sumB = b.get(0) + b.get(1);
            return sumA > sumB ? 1:-1;
        });
        for (int i = 0; i < nums1.length && i < k ; i++) {
            for (int j = 0; j < nums2.length && j < k ; j++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(nums1[i]);
                tmp.add(nums2[j]);
                heap.offer(tmp);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < k && (!heap.isEmpty()); i++) {
            result.add(heap.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kSmallestPairs(new int[]{1,7,11},new int[]{2,4,6},3));
        System.out.println(new Solution().kSmallestPairs(new int[]{1,2},new int[]{3},3));
    }
}
