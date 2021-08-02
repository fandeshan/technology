package network_delay_time;

import java.util.*;

/**
 * @author fandeshan
 * @description 743. 网络延迟时间
 *  有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/2
 */
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer,Graph> nodesMap = new HashMap<>();
        for (int i = 0; i < times.length ; i++) {
            Graph start;
            Graph end;
            if (nodesMap.containsKey(times[i][0])){
                start = nodesMap.get(times[i][0]);
            } else{
                start = new Graph(times[i][0]);
                nodesMap.put(times[i][0],start);
            }
            if (nodesMap.containsKey(times[i][1])){
                end = nodesMap.get(times[i][1]);
            } else{
                end = new Graph(times[i][1]);
                nodesMap.put(times[i][1],end);
            }
            start.others.add(end);
            start.othersLen.add(times[i][2]);
        }
        if (nodesMap.size() < n){
            return -1;
        }
        Map<Graph,Integer> shortestDistanceMap = new HashMap<>();
        Set<Graph> reached = new HashSet<>();
        dfs(nodesMap.get(k),0,shortestDistanceMap,reached);
        if (shortestDistanceMap.size() < n){
            return -1;
        }
        int max = 0;
        for (Map.Entry<Graph,Integer> entry : shortestDistanceMap.entrySet()) {
            max = Math.max(max,entry.getValue());
        }
        return max;
    }
    class Graph {
        int val;
        List<Graph> others;
        List<Integer> othersLen;
        Graph(int val){
            this.val = val;
            others = new ArrayList<>();
            othersLen = new ArrayList<>();
        }
    }
    private void dfs (Graph node,int earlyLen,Map<Graph,Integer> shortestDistanceMap,Set<Graph> reached){
        if (node == null){
            return ;
        }
        if (reached.contains(node)){
            shortestDistanceMap.put(node,Math.min(earlyLen,shortestDistanceMap.get(node)));
            return ;
        } else {
            if (shortestDistanceMap.containsKey(node)){
                shortestDistanceMap.put(node,Math.min(earlyLen,shortestDistanceMap.get(node)));
            } else {
                shortestDistanceMap.put(node,earlyLen);
            }
            if (node.others == null){
                return ;
            }
            for (int i = 0; i < node.others.size(); i++) {

                if (shortestDistanceMap.containsKey(node.others.get(i)) && ( node.othersLen.get(i)+earlyLen >= shortestDistanceMap.get(node.others.get(i)))){
                    continue;
                }
                reached.add(node);
                dfs(node.others.get(i),node.othersLen.get(i)+earlyLen,shortestDistanceMap,reached);
                reached.remove(node);
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2));
        System.out.println(new Solution().networkDelayTime(new int[][]{{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}},5,5));
        System.out.println(new Solution().networkDelayTime(new int[][]{{14,1,8},{11,2,25},{14,15,37},{3,7,70},{11,7,60},{13,11,87},{15,10,67},{13,10,58},{5,4,56},{9,3,26},{5,11,51},{11,4,92},{7,6,8},{7,10,95},{14,9,0},{4,13,1},{7,9,89},{3,14,24},{11,15,30},{13,2,91},{15,8,60},{1,4,96},{8,2,71},{6,8,38},{14,13,46},{2,12,48},{10,11,92},{8,12,28},{8,7,12},{9,13,82},{8,6,27},{3,2,65},{4,10,62},{11,13,55},{1,2,52},{8,3,98},{7,12,85},{6,12,97},{9,4,90},{2,4,23},{9,11,20},{1,14,61},{8,9,77},{6,5,80},{14,11,33},{9,8,54},{13,1,42},{13,8,13},{10,14,40},{9,7,18},{14,3,50},{14,6,83},{14,8,14},{2,1,86},{9,5,54},{11,5,29},{9,12,43},{9,2,74},{14,4,87},{12,7,98},{7,14,13},{4,12,33},{5,2,60},{15,11,33},{8,4,99},{9,6,98},{4,6,57},{6,11,5},{9,15,37},{1,3,30},{9,10,60},{13,12,73},{13,14,56},{1,11,13},{14,2,8},{4,15,60},{11,3,90},{2,5,86},{11,1,1},{13,4,2},{15,7,91},{15,4,51},{11,6,70},{2,7,51},{11,9,37},{4,2,92},{10,4,4},{7,2,30},{13,9,79},{8,15,41},{11,8,18},{15,2,4},{12,14,88},{12,6,9},{12,9,44},{1,6,87},{15,14,42},{4,9,41},{7,15,90},{4,1,84},{7,11,9},{3,11,75},{5,9,2},{2,11,96},{12,5,89},{6,15,25},{5,13,7},{15,5,32},{13,5,84},{7,5,9},{15,3,14},{12,13,4},{5,3,73},{6,9,85},{6,10,29},{1,8,24},{12,3,85},{4,3,60},{1,13,6},{1,5,58},{2,3,29},{14,5,67},{13,15,70},{5,14,94},{15,1,95},{3,1,17},{10,2,6},{11,10,44},{9,14,62},{4,11,32},{15,13,48},{2,10,77},{3,13,90},{5,7,68},{10,6,78},{3,6,95},{10,12,68},{13,6,73},{10,1,8},{10,7,18},{10,5,64},{5,1,55},{13,7,90},{1,9,67},{3,12,76},{14,10,22},{12,8,83},{4,7,76},{8,13,25},{5,6,57},{13,3,90},{6,2,96},{11,14,61},{12,1,94},{12,15,12},{4,8,88},{4,14,27},{7,4,25},{3,9,57},{2,15,90},{1,12,85},{12,11,44},{5,10,13},{5,12,96},{14,7,24},{14,12,98},{10,9,36},{15,6,17},{8,10,11},{2,13,5},{10,3,78},{6,13,11},{5,15,34},{12,10,12},{9,1,68},{10,13,1},{7,13,86},{1,7,62},{2,14,53},{8,14,75},{2,6,49},{10,15,83},{7,8,88},{6,1,87},{8,1,38},{8,11,73},{3,15,1},{3,8,93},{2,8,26},{4,5,26},{3,4,58},{7,1,55},{7,3,84},{5,8,97},{12,4,42},{6,3,71},{6,7,48},{15,12,3},{1,15,30},{10,8,11},{2,9,49},{6,14,95},{3,10,68},{6,4,14},{11,12,29},{1,10,93},{8,5,55},{12,2,86},{3,5,26},{15,9,12}},15,11));
    }
}
