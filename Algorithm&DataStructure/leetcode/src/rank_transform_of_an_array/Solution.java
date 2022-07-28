package rank_transform_of_an_array;

/**
 * @author fandeshan
 * @description 1331. 数组序号转换
 * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
 *
 * 序号代表了一个元素有多大。序号编号的规则如下：
 *
 * 序号从 1 开始编号。
 * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 * 每个数字的序号都应该尽可能地小。
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [40,10,20,30]
 * 输出：[4,1,2,3]
 * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
 * 示例 2：
 *
 * 输入：arr = [100,100,100]
 * 输出：[1,1,1]
 * 解释：所有元素有相同的序号。
 * 示例 3：
 *
 * 输入：arr = [37,12,28,9,100,56,80,5,12]
 * 输出：[5,3,4,2,8,6,7,1,3]
 *  
 *
 * 提示：
 *
 * 0 <= arr.length <= 105
 * -109 <= arr[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rank-transform-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/28
 */
public class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[][] arr2 = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            arr2[i][0] = arr[i];
            arr2[i][1] = i;
        }
        quickSort(arr2,0,arr.length - 1);
        int[] result = new int[arr.length];
        int start = 0;
        for (int i = 0; i < arr2.length; i++) {
            if (i == 0 || arr2[i][0] != arr2[i-1][0]){
                start ++;
            }
            result[arr2[i][1]] =start;

        }
        return result;
    }
    private void quickSort(int[][] arr ,int start ,int end){
        if (start >= end) {
            return ;
        }
        int base = arr[start][0];
        int left = start;
        int right = end;
        while (left <= right){
            while (left <= right && arr[left][0] < base){
                left ++;
            }
            while (left <= right && arr[right][0] > base){
                right --;
            }
            if (left <= right){
                int[] tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left ++;
                right --;
            }
        }
        quickSort(arr,start,right);
        quickSort(arr,left,end);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().arrayRankTransform(new int[]{40,10,20,30}));
        System.out.println(new Solution().arrayRankTransform(new int[]{100,100,100}));
        System.out.println(new Solution().arrayRankTransform(new int[]{37,12,28,9,100,56,80,5,12}));
    }
}
