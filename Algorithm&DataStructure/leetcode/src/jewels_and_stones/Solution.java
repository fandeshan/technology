package jewels_and_stones;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandeshan
 * @description 771. 宝石与石头
 *
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * 示例 2：
 *
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 *
 * @date 2022/10/17
 */
public class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelsSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jewelsSet.add(jewels.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewelsSet.contains(stones.charAt(i))){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numJewelsInStones("aA","aAAbbbb"));
    }
}
