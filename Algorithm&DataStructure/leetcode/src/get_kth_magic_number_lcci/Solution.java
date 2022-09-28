package get_kth_magic_number_lcci;

/**
 * @author fandeshan
 * @description 面试题 17.09. 第 k 个数
 *
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 *
 * 输入: k = 5
 *
 * 输出: 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/28
 */
public class Solution {
    public int getKthMagicNumber(int k) {
        int[] dp=new int[k];
        dp[0]=1;
        int a=0,b=0,c=0;
        for(int i=1;i<k;i++){
            int n3=dp[a]*3,n5=dp[b]*5,n7=dp[c]*7;
            dp[i]=Math.min(n3,Math.min(n5,n7));
            if(dp[i]==n3) a++;
            if(dp[i]==n5) b++;
            if(dp[i]==n7) c++;
        }
        return dp[k-1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getKthMagicNumber(5));
    }
}
