package find_k_closest_elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description 658. 找到 K 个最接近的元素
 *
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *  
 *
 * 提示：
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-closest-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/25
 */
public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int index = halfSearch(arr,x);
        int left = index;
        int right = index;
        for (int i = 1; i < k; i++) {
            if (left -1 < 0){
                right ++;
                continue;
            }
            if (right+1 >= arr.length){
                left --;
                continue;
            }
            if (x - arr[left -1] <= arr[right + 1] - x){
                left --;
            } else {
                right ++;
            }
        }
        for (int i = left; i <=right ; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    private int halfSearch(int[] arr,int num){
        int start = 0;
        int end = arr.length - 1;
        while (start < end){
            int mid = ( start + end )/2;
            if (mid == 0 || mid == arr.length - 1){
                return mid ;
            }
            if (num >= arr[mid - 1] && num<= arr[mid]){
                if (num - arr[mid - 1] > arr[mid]-num){
                    return mid;
                }
                return mid - 1;
            }
            if (num >= arr[mid] && num<= arr[mid+1]){
                if (arr[mid + 1] - num >= num - arr[mid]){
                    return mid;
                }
                return mid + 1;
            }
            if (num < arr[mid]){
                end = mid;
            } else{
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findClosestElements(new int[]{1,25,35,45,50,59},1,30));
        System.out.println(new Solution().findClosestElements(new int[]{3,5,8,10},2,15));
        System.out.println(new Solution().findClosestElements(new int[]{1,1,1,10,10,10},1,9));
        System.out.println(new Solution().findClosestElements(new int[]{1,2,3,4,5},4,3));
        System.out.println(new Solution().findClosestElements(new int[]{1,2,3,4,5},4,-1));
    }
}
