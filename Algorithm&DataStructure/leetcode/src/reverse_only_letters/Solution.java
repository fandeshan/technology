package reverse_only_letters;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/2/23
 */
public class Solution {
    public String reverseOnlyLetters(String s) {
        if (s.length() < 2){
            return s;
        }
        byte[] bs = s.getBytes();
        int left = 0;
        int right = bs.length - 1;
        while (left <= right){
            while (left < right && (!IsLetter(bs[left]))){
                left ++;
            }
            while (left < right && (!IsLetter(bs[right]))){
                right --;
            }
            if (left < right){
                byte temp = bs[left];
                bs[left] = bs[right];
                bs[right] = temp;
            }
            left ++;
            right --;
        }
        return new String(bs);
    }
    private boolean IsLetter(byte a){
        if ((a >='a' && a <='z')||(a>='A'&&a<='Z')){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseOnlyLetters("ab-cd"));
        System.out.println(new Solution().reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(new Solution().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }
}
