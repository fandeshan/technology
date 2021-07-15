package maximum_element_after_decreasing_and_rearranging;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 1846. 减小和重新排列数组后的最大元素
 * @date 2021/7/15
 */

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i =1;i< arr.length;i++){
            if (arr[i]-arr[i-1]>1){
                arr[i] = arr[i-1] + 1;
            }
        }
        return arr[arr.length-1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{2,2,1,2,1}));
        System.out.println(new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{1,100,1000}));
        System.out.println(new Solution().maximumElementAfterDecrementingAndRearranging(new int[]{1,2,3,4,5}));
    }
}
