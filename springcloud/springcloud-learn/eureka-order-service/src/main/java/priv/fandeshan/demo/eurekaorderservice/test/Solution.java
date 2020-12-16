package priv.fandeshan.demo.eurekaorderservice.test;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

public class Solution {
    public int concatenatedBinary(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1 ; i <= n;i ++){
            sb.append(Integer.toBinaryString(i));
        }
        System.out.println(sb.toString());
        BigInteger bi = toNumString(sb.toString());
        return bi.mod(new BigInteger("1000000009")).intValue();
    }
    public BigInteger toNumString(String binaryString){
        int len = binaryString.length();
        BigInteger bi = new BigInteger("0");
        for (int i= 0;i < len;i++){
            if (binaryString.charAt(len - i - 1) =='1'){

            }
        }
        System.out.println(bi);
        return bi;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().concatenatedBinary(3));
        System.out.println(new Solution().concatenatedBinary(12));
    }
}
