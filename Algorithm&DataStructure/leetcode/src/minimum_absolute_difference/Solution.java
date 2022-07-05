package minimum_absolute_difference;

import java.util.*;

/**
 * @author fandeshan
 * @description 1200. 最小绝对差
 *
 * 给你个整数数组 arr，其中每个元素都 不相同。
 *
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 *
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 *
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-absolute-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/4
 */
public class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new ArrayDeque();
        List<Integer> list1 = new ArrayList<>();
        list1.add(arr[0]);
        list1.add(arr[1]);
        queue.add(list1);
        for (int i = 1; i < arr.length - 1; i++) {
            List<Integer> list = new ArrayList<>();
            if (arr[i+1] - arr[i] == min){
                list.add(arr[i]);
                list.add(arr[i+1]);
                queue.add(list);
            } else if (arr[i+1] - arr[i] < min){
                while (queue.size() > 0){
                    queue.poll();
                }
                list.add(arr[i]);
                list.add(arr[i+1]);
                queue.add(list);
                min = arr[i+1] - arr[i];
            }
        }
        while (queue.size() > 0){
            result.add(queue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumAbsDifference(new int[]{40,11,26,27,-20}));
        System.out.println(new Solution().minimumAbsDifference(new int[]{4,2,1,3}));
        System.out.println(new Solution().minimumAbsDifference(new int[]{1,3,6,10,15}));
        System.out.println(new Solution().minimumAbsDifference(new int[]{3,8,-10,23,19,-4,-14,27}));
    }
}
