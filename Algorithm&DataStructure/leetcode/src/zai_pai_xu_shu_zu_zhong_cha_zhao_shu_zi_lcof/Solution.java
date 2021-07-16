package zai_pai_xu_shu_zu_zhong_cha_zhao_shu_zi_lcof;

/**
 * @author fandeshan
 * @description 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * @date 2021/7/16
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0){
            return 0;
        }
        if ( nums[nums.length-1] < target || nums[0] > target ){
            return 0;
        }
        int end = binarySearch(nums,target,true);
        if ( end == -1){
            return 0;
        }
        return  end - binarySearch(nums,target,false) +1;
    }

    private int binarySearch(int[] nums,int target,boolean findRight){
        int left = 0;
        int right = nums.length -1;
        boolean found = false;
        while ( left < right ) {
            int mid = left + (right - left)/2;
            if (nums[mid] > target){
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else{
                found = true;
                if (findRight ){
                    if( mid+1 < nums.length ){
                        if (nums[mid + 1] == target){
                            left = mid + 1;
                        } else {
                            left = mid;
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    if( mid - 1 >= 0 ){
                        if (nums[mid - 1] == target){
                            right = mid - 1;
                        } else {
                            left = mid;
                            break;
                        }
                    } else {
                        break;
                    }

                }
            }

        }
        if(!found){
            if (nums[left] == target){
                return left;
            }
            return -1;
        }
        return left;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{1,4},4));
        System.out.println(new Solution().search(new int[]{1},1));
        System.out.println(new Solution().search(new int[]{5,7,7,8,8,10},8));

        System.out.println(new Solution().binarySearch(new int[]{5,7,7,8,8,10},8,true));
        System.out.println(new Solution().binarySearch(new int[]{5,7,7,8,8,10},8,false));

        System.out.println(new Solution().binarySearch(new int[]{5,7,7,8,8,10},7,true));
        System.out.println(new Solution().binarySearch(new int[]{5,7,7,8,8,10},7,false));

        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,5},5,true));
        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,5},5,false));

        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,5},6,false));
        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,5},6,true));

        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,6},2,false));
        System.out.println(new Solution().binarySearch(new int[]{5,5,5,5,5,6},2,true));
    }
}