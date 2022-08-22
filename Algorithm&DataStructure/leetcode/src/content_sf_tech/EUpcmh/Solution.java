package content_sf_tech.EUpcmh;

import java.time.temporal.ValueRange;
import java.util.*;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/8/22
 */
public class Solution {
    public boolean hasCycle(String graph) {
        if (null == graph || "".equals(graph)){
            return false;
        }
        String[] singleG = graph.split(",");
        Map<String,List<String>> graphMap =new HashMap<>();

        for (int i = 0; i < singleG.length; i++) {
            String[] tmp = singleG[i].split("->");
            if (tmp.length < 2){
                continue;
            }
            List<String> nextList = graphMap.getOrDefault(tmp[0], new ArrayList<>());
            nextList.add(tmp[1]);
            graphMap.put(tmp[0],nextList);

        }

        for (Map.Entry<String,List<String>> entry:graphMap.entrySet()) {
            Set<String> repeat = new HashSet<>();
            boolean flag = checkCycle(graphMap,entry.getKey(),repeat);
            if (flag){
                return true;
            }
        }
        return false;
    }
    private boolean checkCycle(Map<String,List<String>> graphMap,String curr,Set<String> repeat){
        if (repeat.contains(curr)){
            return true;
        }
        List<String> nextList = graphMap.get(curr);
        if (nextList == null || nextList.isEmpty()){
            return false;
        }
        repeat.add(curr);

        for (int i = 0; i < nextList.size(); i++) {
            boolean flag = checkCycle(graphMap,nextList.get(i),repeat);
            repeat.remove(curr);
            if (flag){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println("1->4".split("->")[1]);
        System.out.println(new Solution().hasCycle("1->4,2->5,3->6,3->7,4->8,5->8,5->9,6->9,6->11,7->11,8->12,9->12,9->13,10->13,10->14,11->10,11->14"));
        System.out.println(new Solution().hasCycle("1->4,2->5,3->6,3->7,4->8,5->8,5->9,6->9,6->11,7->11,8->12,9->12,9->13,10->6,10->13,10->14,11->10,11->14"));
    }
}
class Graph{
    int val;
    List<Graph> nexts;
    public Graph(int val){
        this.val = val;
        nexts = new ArrayList<>();
    }
}
