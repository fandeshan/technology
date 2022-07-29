package valid_square;

import java.util.Arrays;

/**
 * @author fandeshan
 * @description 593. 有效的正方形
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 *
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 *
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 *
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 *
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *  
 *
 * 提示:
 *
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/7/29
 */
public class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int len12 = (p1[0] - p2[0])*(p1[0] - p2[0]) + (p1[1] - p2[1])*(p1[1] - p2[1]);
        int len13 = (p1[0] - p3[0])*(p1[0] - p3[0]) + (p1[1] - p3[1])*(p1[1] - p3[1]);
        int len14 = (p1[0] - p4[0])*(p1[0] - p4[0]) + (p1[1] - p4[1])*(p1[1] - p4[1]);
        int len23 = (p2[0] - p3[0])*(p2[0] - p3[0]) + (p2[1] - p3[1])*(p2[1] - p3[1]);
        int len24 = (p2[0] - p4[0])*(p2[0] - p4[0]) + (p2[1] - p4[1])*(p2[1] - p4[1]);
        int len34 = (p3[0] - p4[0])*(p3[0] - p4[0]) + (p3[1] - p4[1])*(p3[1] - p4[1]);
        if (len12 == 0){
            return false;
        }
        if (len12 > len13){
            if (len13!=len14){
                return false;
            }
            if (len12==len34&&len13==len23&&len13==len24 && len13+len14==len12){
                return true;
            }
            return false;
        } else if (len12 == len13){
            if (len14==len23 &&  len12==len34 && len12==len24 && len12+len13==len14){
                return true;
            }
            return false;
        } else{
            if (len13==len24 && len12==len14 && len12==len23 && len12==len34 && len12+len14==len13){
                return true;
            }
            return false;
        }
    }
    public boolean validSquare1(int[] p1, int[] p2, int[] p3, int[] p4) {
        int arr[][] = new int[4][2];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;
        arr[3] = p4;
        Arrays.sort(arr,(a,b)-> a[0]>b[0]?1:(a[0]==b[0]?Integer.compare(a[1],b[1]):-1));
        if (arr[0][0]!=arr[1][0]||arr[0][1]!=arr[2][1]||arr[1][1]!=arr[3][1]||arr[2][0]!=arr[3][0]){
            return false;
        }
        int len = arr[1][1] - arr[0][1];
        if (arr[2][0] - arr[0][0]!=len || arr[3][0] - arr[1][0]!=len || arr[3][1] - arr[2][1]!=len){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 0,0  0,1  1,0  1,1
        System.out.println(new Solution().validSquare(new int[]{1,0},new int[]{0,1},new int[]{0,-1},new int[]{-1,0}));
        System.out.println(new Solution().validSquare(new int[]{0,0},new int[]{1,1},new int[]{1,0},new int[]{0,1}));
        System.out.println(new Solution().validSquare(new int[]{0,0},new int[]{1,1},new int[]{1,0},new int[]{0,12}));
        System.out.println(new Solution().validSquare(new int[]{1,0},new int[]{-1,0},new int[]{0,1},new int[]{0,-1}));
    }
}
