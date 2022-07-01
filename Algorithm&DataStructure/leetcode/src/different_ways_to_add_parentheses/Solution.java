package different_ways_to_add_parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fandeshan
 * @description 241. 为运算表达式设计优先级
 * @date 2022/7/1
 */
public class Solution {
    private Map<String,List<Integer>> resultMap = new HashMap<>();
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression ==""){
            return new ArrayList<>();
        }
        if (resultMap.containsKey(expression)){
            return resultMap.get(expression);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c =='+' || c =='-' ||c=='*'){
                List<Integer> leftResult = diffWaysToCompute(expression.substring(0,i));
                List<Integer> rightResult = diffWaysToCompute(expression.substring(i+1));
                for (int j = 0; j < leftResult.size(); j++) {
                    for (int k = 0; k < rightResult.size(); k++) {
                        if (c == '+'){
                            result.add(leftResult.get(j)+rightResult.get(k));
                        } else if (c == '-'){
                            result.add(leftResult.get(j)-rightResult.get(k));
                        } else {
                            result.add(leftResult.get(j)*rightResult.get(k));
                        }
                    }
                }
            }
        }
        if (result.size() == 0){
            try {
                Integer val = Integer.parseInt(expression);
                result.add(val);
            }catch (Exception e){

            }

        }
        resultMap.put(expression,result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().diffWaysToCompute("2-1-1"));
        System.out.println(new Solution().diffWaysToCompute("2*3-4*5"));
    }
}
