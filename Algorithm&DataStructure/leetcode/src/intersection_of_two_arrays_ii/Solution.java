package intersection_of_two_arrays_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/9/2
 */
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] arr = new int[1001];
        for (int i = 0; i < nums1.length; i++) {
            arr[nums1[i]] ++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (arr[nums2[i]] > 0){
                result.add(nums2[i]);
                arr[nums2[i]] --;
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().intersect(new int[]{1,2,2,1},new int[]{2,2}));
    }
}
