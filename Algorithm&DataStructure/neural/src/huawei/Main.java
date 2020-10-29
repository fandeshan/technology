package huawei;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String originStr = in.next();
        if (originStr != null && !"".equals(originStr)) {
            StringBuffer orginSb = new StringBuffer();
            orginSb = orginSb.append(originStr.charAt(0));

            for(int i = 1; i < originStr.length(); ++i) {
                if (originStr.charAt(i) == '-' && originStr.charAt(i - 1) != '^') {
                    orginSb = orginSb.append("+");
                }

                orginSb = orginSb.append(originStr.charAt(i));
            }

            String[] singles = orginSb.toString().split("\\+");
            Map<Integer, String> indexMap = new TreeMap();
            String[] var6 = singles;
            int var7 = singles.length;

            int indexKey;
            String single;
            int mod;
            for(indexKey = 0; indexKey < var7; ++indexKey) {
                single = var6[indexKey];
                mod = getIndex(single);
                if (indexMap.containsKey(mod)) {
                    String calSingle = (String)indexMap.get(mod);
                    int finalMod = getMod(calSingle) + getMod(single);
                    String calfinalSingle = finalMod + "X^" + mod;
                    indexMap.put(mod, calfinalSingle);
                } else {
                    indexMap.put(mod, single);
                }
            }

            String finalStr = "";

            for(Iterator var16 = indexMap.keySet().iterator(); var16.hasNext(); System.out.println("index:" + indexKey + "value:" + (String)indexMap.get(indexKey))) {
                indexKey = (Integer)var16.next();
                single = (String)indexMap.get(indexKey);
                mod = getMod(single);
                if (mod < 0) {
                    finalStr = single + finalStr;
                } else if (mod > 0) {
                    finalStr = "+" + single + finalStr;
                }
            }

            System.out.println(finalStr);
        } else {
            System.out.println("");
        }
    }

    public static int getIndex(String single) {
        int pos = single.indexOf("^");
        if (pos < 0) {
            return 0;
        } else {
            String indexStr = single.substring(pos + 1, single.length());
            return Integer.parseInt(indexStr);
        }
    }

    public static int getMod(String single) {
        int pos = single.indexOf("X");
        if (pos < 0) {
            return 0;
        } else {
            String indexStr = single.substring(0, pos);
            return Integer.parseInt(indexStr);
        }
    }
}
