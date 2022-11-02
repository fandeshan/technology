package diameter_of_binary_tree;

/**
 * @author fandeshan
 * @description 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * @date 2022/10/25
 */
public class Solution {
    private int maxLen = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfsMaxLen(root);
        return maxLen;
    }
    private int dfsMaxLen(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftMax = dfsMaxLen(node.left);
        int rightMax = dfsMaxLen(node.right);
        maxLen = Math.max(maxLen,leftMax+rightMax);
        return Math.max(leftMax,rightMax)+1;
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