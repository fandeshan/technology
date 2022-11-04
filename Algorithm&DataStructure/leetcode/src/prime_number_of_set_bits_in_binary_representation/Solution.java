package prime_number_of_set_bits_in_binary_representation;

/**
 * @author fandeshan
 * @description 762. 二进制表示中质数个计算置位
 *
 * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 *
 * 计算置位位数 就是二进制表示中 1 的个数。
 *
 * 例如， 21 的二进制表示 10101 有 3 个计算置位。
 *
 *
 * 示例 1：
 *
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 * 示例 2：
 *
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 *
 *
 * 提示：
 *
 * 1 <= left <= right <= 106
 * 0 <= right - left <= 104
 *
 * @date 2022/11/4
 */
public class Solution {
    boolean[] primes = new boolean[33];
    public int countPrimeSetBits(int left, int right) {
        genPrimes();
        int count = 0;
        for (int i = left; i <=right ; i++) {
            int tmp = i;
            int cnt1 = 0;
            while (tmp!=0){
                if ((tmp&1)==1){
                    cnt1 ++;
                }
                tmp = tmp>>1;
            }
            if (primes[cnt1]){
                count ++;
            }
        }
        return count;
    }
    private void genPrimes(){
        primes[2] = true;
        primes[3] = true;
        primes[5] = true;
        primes[7] = true;
        primes[11] = true;
        primes[13] = true;
        primes[17] = true;
        primes[19] = true;
        primes[23] = true;
        primes[29] = true;
        primes[31] = true;
    }
}
