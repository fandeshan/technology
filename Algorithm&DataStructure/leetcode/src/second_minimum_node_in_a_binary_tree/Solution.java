package second_minimum_node_in_a_binary_tree;

/**
 * @author fandeshan
 * @description 671. 二叉树中第二小的节点
 *  给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 *
 *
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 231 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/27
 */

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
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null){
            return -1;
        }
        if (root.left.val == root.right.val){
//            int leftRt = findSecondMinimumValue(root.left);
//            int rightRt = findSecondMinimumValue(root.right);
//            if (leftRt == -1){
//                return rightRt;
//            }
//            if (rightRt == -1){
//                return leftRt;
//            }
//            return Math.min(leftRt,rightRt);
            return myMin(findSecondMinimumValue(root.left),findSecondMinimumValue(root.right));
        } else if (root.left.val > root.right.val){
            return myMin(root.left.val,findSecondMinimumValue(root.right));
        } else {
            return myMin(root.right.val,findSecondMinimumValue(root.left));
        }
    }
    private int myMin(int a,int b){
        if (a == -1 ){
            return b;
        }
        if (b == -1){
            return a;
        }
        return a < b ? a:b;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(5);
        TreeNode root21 = new TreeNode(5);
        TreeNode root22 = new TreeNode(7);
        root.left = root1;
        root.right = root2;
        root2.left = root21;
        root2.right = root22;
        System.out.println(new Solution().findSecondMinimumValue(root));
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