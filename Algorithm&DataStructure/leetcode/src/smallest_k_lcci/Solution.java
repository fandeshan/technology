package smallest_k_lcci;

import java.util.PriorityQueue;

/**
 * @author fandeshan
 * @description 面试题 17.14. 最小K个数
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 *
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/9/3
 */
public class Solution {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>( (a,b)->{
            return a.compareTo(b) ;
        });
        for (int i = 0; i < arr.length; i++) {
            minQueue.offer(arr[i]);
        }
        int[] minArr = new int[k];
        for (int i = 0; i < minArr.length; i++) {
            minArr[i] = minQueue.poll();
        }

        return minArr;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().smallestK(new int[]{1,3,5,7,2,4,6,8},4));
    }
}