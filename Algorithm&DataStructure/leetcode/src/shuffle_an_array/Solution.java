package shuffle_an_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
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
