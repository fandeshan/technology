package contains_duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandeshan
 */
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num :nums){
            if (numsSet.contains(num)){
                return true;
            }
            numsSet.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(new Solution().containsDuplicate(nums));
    }
}
