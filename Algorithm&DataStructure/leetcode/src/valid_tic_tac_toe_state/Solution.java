package valid_tic_tac_toe_state;

/**
 * @author fandeshan
 * @description 794. 有效的井字游戏
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 *
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 * 示例 2：
 *
 *
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 * 示例 3：
 *
 *
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 * Example 4:
 *
 *
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 *  
 *
 * 提示：
 *
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/9
 */
public class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCnt = 0;
        int oCnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'X'){
                    xCnt ++;
                } else if (board[i].charAt(j) == 'O'){
                    oCnt ++;
                }
            }
        }
        if (xCnt < oCnt){
            return false;
        } else if (xCnt > oCnt+1){
            return false;
        } else if (xCnt == oCnt + 1){
            //如果O可以连成三点，则为false，否则为true
            return !checkWin(board,'O');
        } else { //xCnt == oCnt
            //如果X可以连成三点，则为fasle，否则为true
            return !checkWin(board,'X');
        }
    }
    private boolean checkWin(String[] board,char ch){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length() - i; j++) {
                if (board[i].charAt(j) != ch){
                    continue;
                }
                if (i < board.length - 2){
                    if (board[i+1].charAt(j) == ch && board[i+2].charAt(j) == ch ){
                        return true;
                    }
                }
                if (j < board[i].length() - 2){
                    if (board[i].charAt(j+1) == ch && board[i].charAt(j+2) == ch ){
                        return true;
                    }
                }
                if (j < board[i].length() - 2 && i < board.length - 2){
                    if (board[i+1].charAt(j+1) == ch && board[i+2].charAt(j+2) == ch){
                        return true;
                    }
                }
                if (j > 1 && i < board.length - 2){
                    if (board[i+1].charAt(j-1) == ch && board[i+2].charAt(j-2) == ch){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validTicTacToe(new String[]{"O  ","   ","   "}));
        System.out.println(new Solution().validTicTacToe(new String[]{"XOX"," X ","   "}));
        System.out.println(new Solution().validTicTacToe(new String[]{"XXX","   ","OOO"}));
        System.out.println(new Solution().validTicTacToe(new String[]{"XOX","O O","XOX"}));
    }
}
