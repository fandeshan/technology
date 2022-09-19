package sort_array_by_increasing_frequency;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fandeshan
 * @description 1636. 按照频率将数组升序排序
 *
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。 
 *
 * 请你返回排序后的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 *
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 *
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/19
 */
public class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer,Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i],0)+1);
        }
        quickSort(nums,0,nums.length-1,freqMap);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        return nums;
    }
    public void quickSort(int[] nums,int start,int end,Map<Integer,Integer> freqMap){
        if (start >= end){
            return ;
        }
        int base = nums[start];
        int baseFreq = freqMap.get(nums[start]);
        int left = start;
        int right = end;
        while (left <= right){
            while (left <= right && (freqMap.get(nums[left])<baseFreq
                                    ||(freqMap.get(nums[left])==baseFreq && nums[left] > base))){
                left ++;
            }
            while (left <= right && (freqMap.get(nums[right])>baseFreq
                                    ||(freqMap.get(nums[right])==baseFreq && nums[right] < base))){
                right --;
            }
            if (left <= right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }
        quickSort(nums,start,right,freqMap);
        quickSort(nums,left,end,freqMap);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().frequencySort(new int[]{1,1,2,2,2,3}));
        System.out.println(new Solution().frequencySort(new int[]{-1,1,-6,4,5,-6,1,4,1}));
    }
}
