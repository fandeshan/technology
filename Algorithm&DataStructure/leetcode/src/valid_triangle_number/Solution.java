package valid_triangle_number;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 *
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 *
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-triangle-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/4
 */
class Solution {
    public int triangleNumber(int[] nums) {
        if (nums.length < 3){
            return 0;
        }
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                int index = binarySearch(nums,j,nums.length-1,nums[i]+nums[j]);
//                System.out.println(index+" "+j);
                cnt +=(index  - j);

            }
        }
        return cnt;
    }
    private int binarySearch(int[] nums,int start,int end,int target){
        // 找大于 target 的第一个元素
        int pos = start;
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
        System.out.println(new Solution().triangleNumber(new int[]{0,0,0}));
//        System.out.println(new Solution().binarySearch(new int[]{0,0,0},1,2,0));
//        System.out.println(new Solution().binarySearch(new int[]{2,2,3,4},0,3,0));
        System.out.println(new Solution().triangleNumber(new int[]{2,2,3,4}));
    }
}