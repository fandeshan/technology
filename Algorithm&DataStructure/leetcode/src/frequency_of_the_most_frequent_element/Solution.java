package frequency_of_the_most_frequent_element;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 1838. 最高频元素的频数
 * @date 2021/7/19
 */
class Solution {
    public int maxFrequency(int[] nums, int k) {
        if (nums.length == 1 ){
            return 1;
        }
        Arrays.sort(nums);
        int left = 0,right = 0;
        int sum = 0;
        int max = 0;
        while (right < nums.length) {
            while(nums[right]*(right-left)-sum>k){
                sum-=nums[left++];
            }
            sum+=nums[right];
            max=Math.max(max, right-left+1);
            right ++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxFrequency(new int[]{9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966},3056));
        System.out.println(new Solution().maxFrequency(new int[]{1,2,4},5));
        System.out.println(new Solution().maxFrequency(new int[]{1,4,8,13},5));
        System.out.println(new Solution().maxFrequency(new int[]{3,9,6},2));

    }
}