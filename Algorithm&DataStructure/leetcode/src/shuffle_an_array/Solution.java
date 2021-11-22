package shuffle_an_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author fandeshan
 * @description 384. 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *  
 *
 * 示例：
 *
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/11/22
 */
public class Solution {
    private int[] elements;
    public Solution(int[] nums) {
        this.elements = nums;
    }

    public int[] reset() {
        return this.elements;
    }
    public int[] shuffle() {
        int [] s = new int[elements.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        Random r = new Random();
        for (int i = 0; i < s.length; i++) {
            int j = r.nextInt(list.size());
            s[i] = list.remove(j);
        }
        return s;
    }

    public int[] shuffle1() {
        int [] s = new int[elements.length];
        Random r = new Random();
        for (int i = 0; i <s.length ; i++) {
            int ran = Math.abs(r.nextInt(elements.length))%(i+1);
            if (ran != i){
                s[i] = s[ran];
                s[ran] = elements[i];
            } else {
                s[i] = elements[i];
            }
        }
        return s;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1,2,3,4,5,6});
        solution.print(solution.shuffle());
        solution.print(solution.reset());
        solution.print(solution.shuffle());
        solution.print(solution.shuffle());
    }
    private void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
