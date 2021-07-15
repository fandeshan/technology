package minimum_absolute_sum_difference;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 1818. 绝对差值和
 * @date 2021/7/14
 */
class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] result = Arrays.copyOf(nums1,nums1.length);
        Arrays.sort(result);
        int[][] result1 = new int[nums1.length][2];
        int sum = 0;
        for (int i=0; i<nums1.length; i++) {
            result1[i][0] = abs(nums1[i] , nums2[i]);
            result1[i][1] = i;
            sum = (sum + result1[i][0])%1000000007;
        }
        quickSort(result1,0,result1.length-1);
        int adjustMax = 0;
        for (int i = 0;i < result1.length;i ++){
//            int minDiff = calMinDiff(nums1,nums2[result1[i][1]]);
            int n1Index = halfSearch(result,nums2[result1[i][1]]);
            if (adjustMax < result1[i][0] - abs(nums2[result1[i][1]],result[n1Index])){
                adjustMax = result1[i][0] - abs(nums2[result1[i][1]],result[n1Index]);
            }
//            if (result1[i][0] - minDiff <= adjustMax) {
//                break;
//            }
//            adjustMax = result1[i][0] - minDiff;
        }
        return (sum + 1000000007 - adjustMax)%1000000007;

    }
    private int calMinDiff(int[] nums,int target){
        int min = abs(nums[0],target);
        for (int i = 1;i < nums.length;i ++){
            if (min > abs(nums[i],target)){
                min = abs(nums[i],target);
            }
        }
        return min;
    }

    private int abs(int a,int b){
        return a-b > 0? a-b : b-a ;
    }

    private void quickSort(int[][] nums,int start,int end){
        if (end <= start ){
            return ;
        }
        int left = start;
        int right = end;
        int base = nums[start][0];
        while (left <= right ){
            while ( left <= right && nums[left][0] > base){
                left ++;
            }
            while ( left <= right && nums[right][0] < base){
                right --;
            }
            if ( left <= right ){
                int[] tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }
        quickSort(nums,start,right);
        quickSort(nums,left,end);
    }

    private int halfSearch(int[] nums,int target){
        int length=nums.length;
        if(nums[length-1] < target){
            return length-1;
        }
        int left = 0,right = length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        if(left == 0)
            return left;
        return target - nums[left-1] < nums[left] - target?left - 1:left;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().halfSearch(new int[]{1,28,21},21));
        System.out.println(new Solution().minAbsoluteSumDiff(new int[]{1,28,21},new int[]{9,21,20}));
        System.out.println(new Solution().minAbsoluteSumDiff(new int[]{1,7,5},new int[]{2,3,5}));
        System.out.println(new Solution().minAbsoluteSumDiff(new int[]{2,4,6,8,10},new int[]{2,4,6,8,10}));
        System.out.println(new Solution().minAbsoluteSumDiff(new int[]{1,10,4,4,2,7},new int[]{9,3,5,1,7,4}));
    }
}