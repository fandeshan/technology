package sftest;

import java.util.*;

public class Solution {
    public int counterRepeats(int[][] nums){
        Map<Integer,Integer> numCountMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                numCountMap.put(nums[i][j],numCountMap.getOrDefault(nums[i][j],0)+1);
            }
        }
        int[] repeat = genRepeatTimeCountArr(nums.length);
        int sumRepeats = 0;
        for (Map.Entry<Integer,Integer> countEntry:numCountMap.entrySet()) {
            //System.out.println(countEntry.getKey()+","+countEntry.getValue());
            sumRepeats += repeat[countEntry.getValue()];
        }
        return sumRepeats;
    }
    private int[] genRepeatTimeCountArr(int length){
        int[] repeat = new int[length+1];
        repeat[0] = 0;
        repeat[1] = 0;
        int[] facts = genNFacts(length);
        for (int i = 2; i <= length; i++) {
            int j = 1;
            repeat[i] = (int) Math.pow(3,length - i);
            while (i-j > 1){
                repeat[i] += (facts[i]/(facts[i-j]*facts[j]))*((int) Math.pow(3,length - i+j)-j);
                j++;
            }
        }
        return repeat;
    }

    /**
     * 计算n的阶乘返回数组
     * @param n
     * @return
     */
    private int[] genNFacts(int n){
        int[] facts = new int[n+1];
        facts[0] = 0;
        facts[1] = 1;
        for (int i = 2; i <= n; i++) {
            facts[i] = i * facts[i-1];
        }
        return facts;
    }
    private int sumCount = 0;
    public int counterRepeats1(int[][] nums){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        dfs(nums,0,tmpList,results);
        return sumCount;
    }
    private void dfs(int[][] nums,int i,List<Integer> tmpList,List<List<Integer>> results){
        if (i == nums.length){
            Set<Integer> checkRepeat = new HashSet<>();
            for (int j = 0; j < tmpList.size(); j++) {
                if (checkRepeat.contains(tmpList.get(j))){
                    System.out.println(tmpList);
                    sumCount ++;
                    break;
                }
                checkRepeat.add(tmpList.get(j));
            }
            results.add(tmpList);
            return ;
        }
        for (int j = 0; j < nums[i].length; j++) {
            tmpList.add(nums[i][j]);
            dfs(nums,i+1,tmpList,results);
            tmpList.remove(tmpList.size() -1);
        }
    }

    private int sumCount2 = 0;
    public int counterRepeats2(int[][] nums){
        Set<Integer> tmpSet = new HashSet();
        dfs2(nums,0,tmpSet);
        return sumCount2;
    }
    private void dfs2(int[][] nums,int i,Set<Integer> tmpSet){
        if (i == nums.length){
            return ;
        }
        for (int j = 0; j < nums[i].length; j++) {
            if (tmpSet.contains(nums[i][j])){
                sumCount2 += Math.pow(3,nums.length - i -1);
                return ;
            }
            tmpSet.add(nums[i][j]);
            dfs2(nums,i+1,tmpSet);
            tmpSet.remove(nums[i][j]);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().counterRepeats2(new int[][]
                {{1,2,3},{2,3,4},{3,5,6}}));
        System.out.println(new Solution().counterRepeats2(new int[][]
                {{1,2,3},{1,2,3},{1,2,4},{1,5,6}}));
    }
}
