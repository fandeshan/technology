package average_of_levels_in_binary_tree;

/**
 * @author fandeshan
 * @description 637. 二叉树的层平均值
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 * 示例 2:
 *
 *
 *
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 *  
 *
 * 提示：
 *
 * 树中节点数量在 [1, 104] 范围内
 * -231 <= Node.val <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/average-of-levels-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/10/11
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Double> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int len = queue.size();
            int sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add((double)sum/len);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
