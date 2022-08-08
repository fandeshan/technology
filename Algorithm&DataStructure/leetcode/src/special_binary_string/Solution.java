package special_binary_string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String makeLargestSpecial(String s) {
        if (s == null || s.length() < 2){
            return s;
        }
        int cnt = 0,index = 0;
        List<String> subStrs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1'){
                cnt ++;
            } else {
                cnt --;
                if (cnt == 0){
                    subStrs.add("1" + makeLargestSpecial(s.substring(index+1,i))+"0");
                    index = i + 1;
                }
            }
        }
        Collections.sort(subStrs,(a,b) ->b.compareTo(a));
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < subStrs.size(); i++) {
            result.append(subStrs.get(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().makeLargestSpecial("11011000"));
    }
}
