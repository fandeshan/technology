package minimum_time_difference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fandeshan
 * @description 539. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 *
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/1/18
 */
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints,(a,b)->{
            return a.compareTo(b);
        });
        if (timePoints.size() > 24*60){
            return 0;
        }
        int minSub = subTimes(timePoints.get(timePoints.size() - 1),timePoints.get(0));
        for (int i = 1; i < timePoints.size(); i++) {
            minSub = Math.min(subTimes(timePoints.get(i),timePoints.get(i-1)),minSub);
        }
        return minSub;
    }

    private int subTimes(String val1,String val2){
        String[] val1Arr = val1.split(":");
        String[] val2Arr = val2.split(":");
        //int val1Hour = Integer.parseInt(val1Arr[0]);
        //int val1Minu = Integer.parseInt(val1Arr[1]);
        //int val2Hour = Integer.parseInt(val2Arr[0]);
        //int val2Minu = Integer.parseInt(val2Arr[1]);
        int val1Hour = (val1.charAt(0) - '0') * 10 + (val1.charAt(1) - '0');
        int val1Minu = (val1.charAt(3) - '0') * 10 + (val1.charAt(4) - '0');
        int val2Hour = (val2.charAt(0) - '0') * 10 + (val2.charAt(1) - '0');
        int val2Minu = (val2.charAt(3) - '0') * 10 + (val2.charAt(4) - '0');
        if (val2Minu < val2Minu){
            val1Hour --;
            val1Minu += 60;
        }
        int minuSub = val1Minu - val2Minu;
        int result = (val1Hour - val2Hour)*60 + minuSub;
        if (result > 12*60){
            return 24*60 - result;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>();
//        timePoints.add("23:59");
//        timePoints.add("11:12");
//        timePoints.add("01:12");
//        timePoints.add("00:00");
        timePoints.add("12:12");
        timePoints.add("00:13");
        System.out.println(new Solution().findMinDifference(timePoints));
    }
}
