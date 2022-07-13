package asteroid_collision;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author fandeshan
 * @description 735. 行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *  
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/7/13
 */
public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int aster : asteroids) {
            boolean alive = true;
            while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                alive = stack.peek() < -aster; // aster 是否存在
                if (stack.peek() <= -aster) {  // 栈顶行星爆炸
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(aster);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
    //案例[-2,-1,1,2]不通过
    public int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            while (true){
                if (stack.isEmpty()){
                    break;
                }
                int top = stack.peek();
                if ( (top < 0 &&asteroids[i]>0) ||(top > 0 &&asteroids[i] < 0)){
                    if (abs(top)>abs(asteroids[i])){
                        break;
                    } else if(abs(top)==abs(asteroids[i])){
                        stack.pop();
                        break;
                    } else{
                        stack.pop();
                        continue;
                    }
                }else{
                    stack.push(asteroids[i]);
                    break;
                }
            }

        }
        int[] result = new int[stack.size()];
        int i = 0;
        while (i<stack.size()){
            result[i]=stack.get(i);
            i++;
        }
//        for (int j = 0; j < result.length; j++) {
//            System.out.print(result[j]+",");
//        }
//        System.out.println();
        return result;
    }
    private int abs(int a){
        if (a < 0){
            return -a;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().asteroidCollision(new int[]{5,10,-5}));
        System.out.println(new Solution().asteroidCollision(new int[]{8,-8}));
        System.out.println(new Solution().asteroidCollision(new int[]{10,2,-5}));
    }
}
