package poor_pigs;

/**
 * @author fandeshan
 * @description 458. 可怜的小猪
 * 有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 *
 * 喂猪的规则如下：
 *
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 * 示例 2：
 *
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 * 示例 3：
 *
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= buckets <= 1000
 * 1 <= minutesToDie <= minutesToTest <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/poor-pigs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/25
 */

public class Solution {
    /**
     *   1  2  3  4  5
     *   6  7  8  9 10
     *  11 12 13 14 15
     *  16 17 18 19 20
     *  21 22 23 24 25
     *  假设有如上 5*5 的桶，一个猪负责一个维度，比如有两个猪，一个负责行，一个负责列
     *  第一次，一只猪负责第一行，一只猪负责第一列，都没死
     *  第二次，一只猪负责第二行，一只猪负责第二列，都没死
     *  ....
     *  第四次，一只猪负责第四行，一只猪负责第四列，都没死
     *  即找到毒药
     *
     * 得 四次能找到 (4+1)^2个桶
     *
     *
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        while (Math.pow(minutesToTest/minutesToDie+1,pigs) < buckets){
            pigs ++;
        }
        return pigs;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().poorPigs(1000,15,60));
        System.out.println(new Solution().poorPigs(4,15,15));
        System.out.println(new Solution().poorPigs(4,15,30));
    }
}
