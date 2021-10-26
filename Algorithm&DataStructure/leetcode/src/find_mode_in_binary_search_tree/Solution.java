package find_mode_in_binary_search_tree;

/**
 * @author fandeshan
 * @description 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/10/22
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Integer,Integer> cntMap;
    private int max;
    public int[] findMode(TreeNode root) {
        cntMap = new HashMap<>();
        max = -1;
        dfs(root);
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry:cntMap.entrySet()) {
            if (max == entry.getValue()){
                result.add(entry.getKey());
            }
        }
        int[] rst = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            rst[i] = result.get(i);
        }
        return rst;
    }

    private void dfs(TreeNode node){
        if (node == null){
            return ;
        }
        int cnt = cntMap.getOrDefault(node.val,0)+1;
        max = Math.max(cnt,max);
        cntMap.put(node.val,cnt);
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node21 = new TreeNode(2);
        node.left = node1;
        node.right = node2;
        node2.left = node21;

        System.out.println(new Solution().findMode(node));
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