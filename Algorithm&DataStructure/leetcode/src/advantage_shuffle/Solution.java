package advantage_shuffle;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 870. 优势洗牌
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 *
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 *
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/advantage-shuffle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/10/8
 */
public class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int[][] num2i = new int[nums2.length][2];
        for (int i = 0; i < nums2.length; i++) {
            num2i[i][0] = nums2[i];
            num2i[i][1] = i;
        }
        quickSort(num2i,0,num2i.length-1);
        Arrays.sort(nums1);
        int i =0,j = nums1.length -1;
        int newNums1[] = new int[nums1.length];
        int k = j;
        while ( i <= j ){
            if (num2i[k][0] < nums1[j]){
                newNums1[num2i[k][1]] = nums1[j];
                j--;
            }else{
                newNums1[num2i[k][1]] = nums1[i];
                i++;
            }
            k --;
        }
        for (int l = 0; l < newNums1.length; l++) {
            System.out.print(newNums1[l]+" ");
        }
        System.out.println();
        return newNums1;
    }
    private void quickSort(int[][] nums,int start,int end){
        if (end <= start ){
            return ;
        }
        int left = start;
        int right = end;
        int base = nums[start][0];
        while (left <= right ){
            while ( left <= right && (nums[left][0] < base )){
                left ++;
            }
            while ( left <= right && (nums[right][0] > base )){
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

    public static void main(String[] args) {
        System.out.println(new Solution().advantageCount(new int[]{2,7,11,15},new int[]{1,10,4,11}));
        System.out.println(new Solution().advantageCount(new int[]{12,24,8,32},new int[]{13,25,32,11}));
    }
}
