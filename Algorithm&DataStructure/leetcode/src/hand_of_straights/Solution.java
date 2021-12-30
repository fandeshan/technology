package hand_of_straights;

import java.util.*;

/**
 * @author fandeshan
 * @description 846. 一手顺子
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 *
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 *
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 *  
 *
 * 提示：
 *
 * 1 <= hand.length <= 104
 * 0 <= hand[i] <= 109
 * 1 <= groupSize <= hand.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hand-of-straights
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/30
 */
public class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : hand) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        for (int x : hand) {
            if (!cnt.containsKey(x)) {
                continue;
            }
            for (int j = 0; j < groupSize; j++) {
                int num = x + j;
                if (!cnt.containsKey(num)) {
                    return false;
                }
                cnt.put(num, cnt.get(num) - 1);
                if (cnt.get(num) == 0) {
                    cnt.remove(num);
                }
            }
        }
        return true;
    }
    public boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0){
            return false;
        }
        Arrays.sort(hand);
        List<Integer> arr = new LinkedList<>();
        for (int i = 0; i < hand.length; i++) {
            arr.add(hand[i]);
        }
        while (arr.size() > 0){
            int tmp = arr.remove(0);
            int j = 0;
            for (int i = 1; i < groupSize; i++) {
                tmp ++;
                boolean found = false;
                for (; j < arr.size(); j++) {
                    if (arr.get(j) == tmp){
                        arr.remove(j);
                        found = true;
                        break;
                    } else if (arr.get(j) > tmp){
                        return false;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3));
    }
}
