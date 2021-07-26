package longest_increasing_subsequence;

/**
 * @author fandeshan
 * @description 300. 最长递增子序列
 *  给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *  
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/7/26
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int len = 1;
        int[] d = new int[nums.length + 1 ];
        d[len] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > d[len] ) {
                d[++len] = nums[i];
            } else {
                int pos = binarySearch(d,1,len,nums[i]);
                d[pos+1] = nums[i];
            }
        }
//        for (int i = 0; i < d.length; i++) {
//            System.out.print (d[i]+" ");
//        }
//        System.out.println();
        return len;
    }

    private int binarySearch(int[] nums,int start,int end,int target){
        // 找大于 target 的第一个元素
        int pos = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                pos = mid;
                start = mid + 1;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(new Solution().lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(new Solution().lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }
}
