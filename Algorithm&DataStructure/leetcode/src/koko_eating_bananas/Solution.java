package koko_eating_bananas;

/**
 * @author fandeshan
 * @description 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 *
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 *
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/6/7
 */
public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lowSp = 1;
        int highSp = 0;
        for (int i = 0; i < piles.length; i++) {
            highSp = Math.max(highSp,piles[i]);
        }
        while (lowSp < highSp){
            int midSp = (highSp - lowSp)/2 + lowSp;
            int hour = getHour(piles,midSp);
            if(hour <= h){
                highSp = midSp;
            } else {
                lowSp = midSp  + 1;
            }
        }
        return lowSp;
    }
    private int getHour(int[] piles,int speed){
        int hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += (piles[i]- 1)/speed + 1;
        }
        return hour;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minEatingSpeed(new int[]{3,6,7,11},8));
        System.out.println(new Solution().minEatingSpeed(new int[]{30,11,23,4,20},5));
        System.out.println(new Solution().minEatingSpeed(new int[]{30,11,23,4,20},6));
    }
}
