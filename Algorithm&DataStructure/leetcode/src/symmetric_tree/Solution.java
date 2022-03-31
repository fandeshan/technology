package symmetric_tree;

/**
 * @author fandeshan
 * @description 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/31
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
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return isSameTree(root.left,root.right);
    }
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val){
            return false;
        }
        return  isSameTree(p.left,q.right) && isSameTree(p.right,q.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode l11 = new TreeNode(3);
        TreeNode l12 = new TreeNode(4);
        TreeNode r1 = new TreeNode(2);
        TreeNode r11 = new TreeNode(4);
        TreeNode r12 = new TreeNode(3);
        root.left = l1;
        root.right = r1;
        l1.left = l11;
        l1.right = l12;
        r1.left = r11;
        r1.right = r12;
        System.out.println(new Solution().isSymmetric(root));
        TreeNode r2 = new TreeNode(1);
        TreeNode r21 = new TreeNode(2);
        TreeNode r212 = new TreeNode(3);
        TreeNode r22 = new TreeNode(2);
        TreeNode r222 = new TreeNode(3);
        r2.left = r21;
        r2.right = r22;
        r21.right = r212;
        r22.right = r222;
        System.out.println(new Solution().isSymmetric(r2));
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
