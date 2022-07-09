package length_of_longest_fibonacci_subsequence;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandeshan
 * @description 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 *  
 *
 * 示例 1：
 *
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 *
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *  
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/9
 */
public class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            nums.add(arr[i]);
        }
        int max = arr[arr.length - 1];
        int maxLen =0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int a1 = arr[i];
                int a2 = arr[j];
                int len = 2;
                while (a1 + a2 <= max){
                    if (!nums.contains(a1+a2)){
                        break;
                    }
                    len ++;
                    maxLen = Math.max(maxLen,len);
                    int tmp = a2;
                    a2=a1+a2;
                    a1=tmp;
                }
            }
            if (maxLen >= (arr.length -i)){
                break;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(new Solution().lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}));
    }
}
