package content_sf_tech._8oimK4;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/8/22
 */
public class Solution {
    public int findMaxCI(int[] nums) {
        if (nums.length < 2){
            return nums.length;
        }
        int left = 0;
        int right = left +1;
        int max = 0;
        while (right < nums.length){
            if (nums[right] > nums[right - 1]){
                right ++;
            }else {
                max = Math.max(max,right - left);
                left = right;
                right++;
            }
        }
        max = Math.max(max,right - left);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMaxCI(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57}));
    }
}
