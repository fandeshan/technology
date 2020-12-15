import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private static final int[] primeList = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long,Integer> resultMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        int j =0;
        for (String str :strs){
            long multiVal  = multi(str);
            if (resultMap.containsKey(multiVal)){
                int index = resultMap.get(multiVal);
                List<String> tmpList = result.get(index);
                tmpList.add(str);
                result.set(index,tmpList);
            } else {
                List<String> tmpList = new ArrayList<>();
                tmpList.add(str);
                result.add(tmpList);
                resultMap.put(multiVal,j++);
            }
        }
        return result;
    }

    public long multi(String str) {
        long multiVal = 1;
        for (byte b :str.getBytes()){
            multiVal *= primeList[b-'a'];
        }
        return multiVal;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().groupAnagrams(new []String{}));
    }
}