package successor_lcci;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/5/16
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode node = inorderSuccessor(root.left, p);
        return node == null ? root : node;
    }


    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(2);
        TreeNode r11 = new TreeNode(1);
        TreeNode r12 = new TreeNode(3);
        r1.left = r11;
        r1.right = r12;
        System.out.println(new Solution().inorderSuccessor(r1,r11));
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

