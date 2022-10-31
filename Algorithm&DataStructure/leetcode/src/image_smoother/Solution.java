package image_smoother;

/**
 * @author fandeshan
 * @description 907. 子数组的最小值之和
 *
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 *
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 *
 * @date 2022/10/28
 */
public class Solution {
    int[][] step = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public int[][] imageSmoother(int[][] img) {
        int[][] newImg = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                int cnt = 1;
                int sum = img[i][j];

                for (int k = 0; k < step.length; k++) {
                    if (i+step[k][0] <0 || i + step[k][0] >= img.length){
                        continue;
                    }
                    if (j+step[k][1] <0 || j + step[k][1] >= img[i].length){
                        continue;
                    }
                    sum +=img[i+step[k][0]][j+step[k][1]];
                    cnt ++;
                }
                newImg[i][j] = sum/cnt;
            }
        }
        return newImg;
    }



    public static void main(String[] args) {
        System.out.println(new Solution().imageSmoother(new int[][]{{100,200,100},{200,50,200},{100,200,100}}));
    }
}
