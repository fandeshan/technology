package missing_two_lcci;

/**
 * @author fandeshan
 * @description 面试题 17.19. 消失的两个数字
 * @date 2022/9/26
 */
public class Solution {
    public int[] missingTwo(int[] nums) {
        int realLen = nums.length + 2;
        int twoSum = sum(realLen);
        for(int i = 0; i < nums.length ; i++){
            twoSum -= nums[i];
        }
        int fistNum = sum(twoSum/2);
        for(int i = 0; i < nums.length ; i++){
            if(nums[i]<=twoSum/2){
                fistNum -= nums[i];
            }
        }
        int[] ans = {fistNum, twoSum-fistNum};
        return ans;
    }

    public int sum (int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().missingTwo(new int[]{2,3}));
    }
}
