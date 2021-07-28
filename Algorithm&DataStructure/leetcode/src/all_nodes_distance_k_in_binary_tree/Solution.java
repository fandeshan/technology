package all_nodes_distance_k_in_binary_tree;

/**
 * @author fandeshan
 * @description 863. 二叉树中所有距离为 K 的结点
 *  给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *  
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/7/28
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        setParent(root,parentMap);
        Set<TreeNode> checkRepeat = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        findDistanceK(target,parentMap,k,result,checkRepeat);
        return result;
    }
    private void setParent(TreeNode node,Map<TreeNode,TreeNode> parentMap){
        if (node == null ){
            return ;
        }
        if (node.left != null){
            parentMap.put(node.left,node);
            setParent(node.left,parentMap);
        }
        if (node.right != null){
            parentMap.put(node.right,node);
            setParent(node.right,parentMap);
        }
    }
    private void findDistanceK(TreeNode target, Map<TreeNode,TreeNode> parentMap, int k, List<Integer> result, Set<TreeNode> checkRepeat){

        if (target == null){
            return ;
        }
        if (checkRepeat.contains(target)){
            return ;
        }
        checkRepeat.add(target);
        if (k == 0) {
            result.add(target.val);
            return ;
        }
        findDistanceK(target.left,parentMap,k-1,result,checkRepeat);
        findDistanceK(target.right,parentMap,k-1,result,checkRepeat);
        if(!parentMap.containsKey(target)){
            return ;
        }
        findDistanceK(parentMap.get(target),parentMap,k-1,result,checkRepeat);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode root1 = new TreeNode(5);
        TreeNode root2 = new TreeNode(1);
        TreeNode root11 = new TreeNode(6);
        TreeNode root12 = new TreeNode(2);
        TreeNode root121 = new TreeNode(7);
        TreeNode root122 = new TreeNode(4);
        TreeNode root21 = new TreeNode(0);
        TreeNode root22 = new TreeNode(8);
        root.left = root1;
        root.right = root2;
        root1.left = root11;
        root1.right = root12;
        root12.left = root121;
        root12.right = root122;
        root2.left = root21;
        root2.right = root22;
        System.out.println(new Solution().distanceK(root,root1,2));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}