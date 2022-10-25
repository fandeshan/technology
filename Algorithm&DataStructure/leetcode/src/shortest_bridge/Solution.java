package shortest_bridge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author fandeshan
 * @description 934. 最短的桥
 *
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 *
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 *
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 *
 * 返回必须翻转的 0 的最小数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 *
 * 提示：
 *
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 *
 * @date 2022/10/25
 */
public class Solution {
    int[][] steps = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int showBridge = Integer.MAX_VALUE;
    public int shortestBridge(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> fistArea = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            int j = 0;
            for (; j < grid[i].length; j++) {
                if (grid[i][j] == 1){
                    fistArea.offer(new int[]{i,j});
                    visited[i][j] = true;
                    dfsOne(grid,visited,fistArea,i,j);
                    break;
                }
            }
            if (j < grid[i].length){
                break;
            }
        }
        return bfsOtherArea(grid,visited,fistArea);
    }
    private void dfsOne(int[][] grid,boolean[][] visited,Queue<int[]> fistArea,int i,int j){
        for (int k = 0; k < steps.length; k++) {
            int newI = i + steps[k][0];
            int newJ = j + steps[k][1];
            if (newI < 0 || newI >= grid.length){
                continue;
            }
            if (newJ < 0 || newJ >= grid[0].length){
                continue;
            }
            if (grid[newI][newJ] != 1){
                continue;
            }
            if (visited[newI][newJ]){
                continue;
            }
            visited[newI][newJ] = true;
            fistArea.add(new int[]{newI,newJ});
            dfsOne(grid,visited,fistArea,newI,newJ);
        }
    }
    private int bfsOtherArea(int[][] grid,boolean[][] visited,Queue<int[]> fistArea){
        int bridge = 0;
        while (!fistArea.isEmpty()){
           int len = fistArea.size();
            for (int i = 0; i < len; i++) {
                int[] xy = fistArea.poll();
                int x = xy[0];
                int y = xy[1];
                for (int j = 0; j < steps.length; j++) {
                    int newI = x + steps[j][0];
                    int newJ = y + steps[j][1];
                    if (newI < 0 || newI >= grid.length){
                        continue;
                    }
                    if (newJ < 0 || newJ >= grid[0].length){
                        continue;
                    }
                    if (visited[newI][newJ]){
                        continue;
                    }
                    if (grid[newI][newJ] == 1){
                        return bridge;
                    }
                    visited[newI][newJ] = true;
                    fistArea.offer(new int[]{newI,newJ});
                }

            }
            bridge ++;
        }

        return bridge;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println( new Solution().shortestBridge(new int[][]{{0,0,0,0,0,0,0},{0,1,0,0,0,0,0},{0,1,1,0,0,0,0},{0,1,1,0,0,0,0},{0,0,0,0,0,0,0},{1,1,0,0,0,0,0},{1,1,0,0,0,0,0}}));
        //System.out.println(System.currentTimeMillis() - start);
        System.out.println( new Solution().shortestBridge(new int[][]{{0,1,0},{0,0,0},{0,0,1}}));
        System.out.println( new Solution().shortestBridge(new int[][]{{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}}));
    }
}
