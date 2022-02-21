package push_dominoes;

/**
 * @author fandeshan
 * @description 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 *
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 *
 *
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *  
 *
 * 提示：
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/push-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/2/21
 */
public class Solution {
    public String pushDominoes(String dominoes) {
        if (dominoes.length() < 2){
            return dominoes;
        }
        int startIndex = 0;
        int endIndex = 1;
        byte[] dominoesBytes = dominoes.getBytes();
        while (endIndex <= dominoesBytes.length){
            while (endIndex < dominoesBytes.length && dominoesBytes[endIndex] == '.'){
                endIndex ++;
            }
            if (endIndex >= dominoesBytes.length){
                if (dominoesBytes[startIndex] == 'R'){
                    for (int i = startIndex + 1; i < dominoesBytes.length; i++) {
                        dominoesBytes[i] = 'R';
                    }
                }
                return new String(dominoesBytes);
            }
            if (dominoesBytes[endIndex] == 'L'){
                if (dominoesBytes[startIndex] == 'R'){
                    int left = startIndex;
                    int right = endIndex;
                    while (left <= right){
                        if (left >= right){
                            break;
                        }
                        dominoesBytes[left] = 'R';
                        dominoesBytes[right] = 'L';
                        left ++;
                        right --;
                    }
                } else {
                    for (int i = startIndex; i < endIndex; i++) {
                        dominoesBytes[i] = 'L';
                    }
                }
                startIndex = endIndex + 1;
            } else if (dominoesBytes[endIndex] == 'R'){
                if (dominoesBytes[startIndex] == 'R'){
                    for (int i = startIndex; i < endIndex; i++) {
                        dominoesBytes[i] = 'R';
                    }
                }
                startIndex = endIndex;
            }
            endIndex = startIndex + 1;
            if (startIndex >= dominoesBytes.length ){
                break;
            }
        }
        return new String(dominoesBytes);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pushDominoes("R.R.L"));
        System.out.println(new Solution().pushDominoes("RR.L"));
        System.out.println(new Solution().pushDominoes(".L.R...LR..L.."));
    }
}
