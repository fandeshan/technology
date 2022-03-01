package zigzag_conversion;

/**
 * @author fandeshan
 * @description 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *  
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/1
 */
public class Solution {
    public String convert(String s, int numRows) {
        if (s.length() < 2 || numRows < 2){
            return s;
        }
        int halfZ = numRows + numRows -2;
        int workStep = (numRows - 2) + 1;
        int col = (s.length()/halfZ + 1) * workStep;
        int row = numRows;
        byte[][] retBytes = new byte[row][col];
        byte[] sBytes = s.getBytes();
        for (int k = 0, i = 0; i < col ;i += workStep)  {
            for (int j = 0;j < row  && k <s.length() ; j ++)  {
                retBytes[j][i] = sBytes[k];
                k ++;
            }
            for (int l = 1; l <= numRows -2 && k <s.length() ; l++)  {
                retBytes[row-l-1][i+l] = sBytes[k];
                k ++;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < row ; i++ ) {
            for (int j = 0; j < col ; j++)  {
                if (retBytes[i][j] != 0) {
                    sb.append((char)(retBytes[i][j]));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING",3));
        System.out.println(new Solution().convert("PAYPALISHIRING",4));
    }
}
