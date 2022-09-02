package longest_univalue_path;

/**
 * @author fandeshan
 * @description 687. 最长同值路径
 *
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 *
 *
 *
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *  
 *
 * 提示:
 *
 * 树的节点数的范围是 [0, 104] 
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/9/2
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
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
    private int maxLen = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfsMaxLen(root,root.val);
        return maxLen;
    }
    private int dfsMaxLen(TreeNode node,int val){
        if (node == null){
            return 0;
        }
        int leftMax = dfsMaxLen(node.left,node.val);
        int rightMax = dfsMaxLen(node.right,node.val);
        maxLen = Math.max(maxLen,leftMax+rightMax);
        if (node.val == val){
            return Math.max(leftMax,rightMax) + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        System.out.println(new Solution().longestUnivaluePath(root));
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
