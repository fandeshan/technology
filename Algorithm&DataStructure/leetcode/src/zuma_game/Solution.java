package zuma_game;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 488. 祖玛游戏
 * 你正在参与祖玛游戏的一个变种。
 *
 * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
 *
 * 你的目标是 清空 桌面上所有的球。每一回合：
 *
 * 从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
 * 接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
 * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
 * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
 * 重复这个过程，直到你赢了游戏或者手中没有更多的球。
 * 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = "WRRBBW", hand = "RB"
 * 输出：-1
 * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
 * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
 * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
 * 桌面上还剩着球，没有其他球可以插入。
 * 示例 2：
 *
 * 输入：board = "WWRRBBWW", hand = "WRBRW"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
 * - 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 * 示例 3：
 *
 * 输入：board = "G", hand = "GGGGG"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'G' ，使桌面变为 GG 。
 * - 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 * 示例 4：
 *
 * 输入：board = "RBYYBBRRB", hand = "YRBGB"
 * 输出：3
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
 * - 插入一个 'B' ，使桌面变为 BB 。
 * - 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
 * 只需从手中出 3 个球就可以清空桌面。
 *  
 *
 * 提示：
 *
 * 1 <= board.length <= 16
 * 1 <= hand.length <= 5
 * board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
 * 桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuma-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/11/9
 */
public class Solution {
    /*
    在一次消除完成后，若没有步数更少的方案，则将其记录到min
    boardStatus用于描述盘面情况，非常重要
     */
    int min = -1, boardStatus;

    public int findMinStep(String board, String hand) {
        int bLen = board.length();
        //真正用于计算和处理的数组，由于存在插入的情况，所以对于原数组以0进行了分割
        char[] line = new char[(bLen << 1) - 1];

        for (int i = 0; i < board.length(); i++) {
            line[i << 1] = board.charAt(i);
            //录入数据的同时记录状态
            boardStatus ^= 1 << (i << 1);
        }

        char[] hands = hand.toCharArray();
        //对数组排序的目的是让相同的手牌集中到一起，方便遍历的时候进行剪枝
        Arrays.sort(hands);
        //搜索
        dfs(line, hands, hand.length());
        return min;
    }

    void dfs(char[] board, char[] hand, int remain) {
        //表明已全部消除，原手牌与剩余手牌的差即步数
        if (boardStatus == 0) {
            int res = hand.length - remain;
            if (min == -1)
                min = res;
            else if (res < min)
                min = res;
            return;
        }

        //手牌用完还没有全消除，此次搜索失败
        if (remain == 0)
            return;

        char val = 0;
        for (int i = 0; i < board.length; i++) {
            //跳过被干掉的球（或者本身就存在的空格）以及跟上一个球同色的球（因为两者是被视为一组进行处理的）
            //PS：本身就存在的空格指最开始为了满足插入操作而填充的0，这些0有可能会通过插入变为“有球”状态并参与消除
            if ((boardStatus & (1 << i)) == 0 || board[i] == val)
                continue;

            //记录颜色（即“下次循环的上一个颜色”）
            val = board[i];
            //寻找下一个有效的球（如果存在）是否与此球同色
            int next = sameNext(board, i);

            //同色
            if (next != -1) {//double set
                //insert a ball with another color
                for (int j = 0; j < hand.length; j++) {
                    if (hand[j] != 0 && hand[j] != val && (j == 0 || hand[j - 1] != hand[j])) {
                        //save status
                        int mirror = boardStatus;
                        char temp = hand[j];
                        if (board[next - 1] != 0)
                            continue;
                        board[next - 1] = temp;
                        hand[j] = 0;
                        boardStatus ^= 1 << (next - 1);
                        dfs(board, hand, remain - 1);
                        //back track
                        boardStatus = mirror;
                        hand[j] = temp;
                    }
                }
                //pop this double with one from hand
                int j = 0;
                while (j < hand.length && hand[j] != val)
                    j++;
                if (j < hand.length) {
                    //play hand[j] and pop
                    int mirror = boardStatus;
                    hand[j] = 0;
                    boardStatus ^= 1 << i | 1 << next;
                    checkThree(board, i - 1, next + 1);
                    dfs(board, hand, remain - 1);
                    //back track
                    hand[j] = val;
                    boardStatus = mirror;
                }
            } else { // next == -1
                //pop single with double from hand
                int left = 0;
                while (left < hand.length && hand[left] != val)
                    left++;
                if (left < hand.length - 1) {
                    int right = hand.length - 1;
                    while (right > left && hand[right] != val)
                        right--;
                    if (left < right) {
                        //save
                        int mirror = boardStatus;
                        hand[left] = hand[right] = 0;
                        boardStatus ^= 1 << i;
                        checkThree(board, i - 1, i + 1);
                        dfs(board, hand, remain - 2);
                        //back track
                        hand[left] = hand[right] = val;
                        boardStatus = mirror;
                    }
                }
            }
        }
    }

    int sameNext(char[] board, int i) {
        int val = board[i];
        while (++i < board.length)
            if ((boardStatus & 1 << i) != 0)
                return board[i] == val ? i : -1;
        return -1;
    }

    //Check combo pop after a pop by playing a ball.
    void checkThree(char[] board, int left, int right) {
        //过滤无效位
        while (left >= 0 && (boardStatus & 1 << left) == 0)
            left--;
        while (right < board.length && (boardStatus & 1 << right) == 0)
            right++;

        if (left < 0 || right >= board.length || board[left] != board[right])
            return;

        //没有return，说明left和right处的球同色，继续向两端搜索同色球
        char val = board[left];

        int l = left - 1, r = right + 1;
        boolean pop = false;
        while (l >= 0) {
            if ((boardStatus & 1 << l) != 0) {
                if (board[l] == val) {
                    pop = true;
                    boardStatus ^= 1 << l;
                }
                break;
            } else
                l--;
        }
        while (r < board.length) {
            if ((boardStatus & 1 << r) != 0) {
                if (board[r] == val) {
                    pop = true;
                    boardStatus ^= 1 << r;
                }
                break;
            } else
                r++;
        }
        //表面有超过3个同色相邻的球，触发自动消除，并进一步递归检测
        if (pop) {
            //由于触发了消除，将两个同色的球位也置为“无效”
            boardStatus ^= 1 << left | 1 << right;
            checkThree(board, l, r);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMinStep("WRRBBW","RB"));
        System.out.println(new Solution().findMinStep("WWRRBBWW","WRBRW"));
        System.out.println(new Solution().findMinStep("G","GGGGG"));
        System.out.println(new Solution().findMinStep("RBYYBBRRB","YRBGB"));
    }
}
