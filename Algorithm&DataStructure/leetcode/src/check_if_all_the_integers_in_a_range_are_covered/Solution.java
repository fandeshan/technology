package check_if_all_the_integers_in_a_range_are_covered;

/**
 * @author fandeshan
 * @description 1893. 检查是否区域内所有整数都被覆盖
 *
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 *
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *  
 *
 * 提示：
 *
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/23
 */
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        quickSort(ranges,0,ranges.length-1);
        for (int i = 0; i < ranges.length; i++) {
            System.out.print(ranges[i][0]+"," + ranges[i][1]+" ");
        }
        System.out.println();
        int[][] newRanges = new int[ranges.length][2];
        newRanges[0] = ranges[0];
        int j = 0;
        for (int i = 1; i < ranges.length; i++) {
            if (ranges[i][0] <= ranges[i-1][1]+1){
                newRanges[j][1] = Math.max(ranges[i][1],newRanges[j][1]);
            } else {
                j++;
                newRanges[j][0] = ranges[i][0];
                newRanges[j][1] = ranges[i][1];
            }
        }
        for (int i = 0; i < newRanges.length; i++) {
            System.out.print(newRanges[i][0]+"," + newRanges[i][1]+" ");
        }
        System.out.println();
        for (int i = left; i <= right ; i++) {
            boolean check = false;
            for (int k = 0; k < newRanges.length && newRanges[k][0]!=0 ; k++) {
                if (i >= newRanges[k][0] && i <= newRanges[k][1]){
                    check = true;
                    break;
                }
            }
            if (check == false){
                return false;
            }
        }
        return true;
    }
    private void quickSort(int[][] nums,int start,int end){
        if (end <= start ){
            return ;
        }
        int left = start;
        int right = end;
        int base = nums[start][0];
        int base1 = nums[start][1];
        while (left <= right ){
            while ( left <= right && (nums[left][0] < base || (nums[left][0] == base && nums[left][1] < base1 ))){
                left ++;
            }
            while ( left <= right && (nums[right][0] > base || (nums[right][0] == base && nums[right][1] > base1 ))){
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
        System.out.println(new Solution().isCovered(new int[][]{{1,2},{3,4},{5,6}},2,5));
        System.out.println(new Solution().isCovered(new int[][]{{1,10},{10,20}},21,21));
        System.out.println(new Solution().isCovered(new int[][]{{1,10},{12,20}},12,13));
        System.out.println(new Solution().isCovered(new int[][]{{25,42},{7,14},{2,32},{25,28},{39,49},{1,50},{29,45},{18,47}},15,38));

    }
}