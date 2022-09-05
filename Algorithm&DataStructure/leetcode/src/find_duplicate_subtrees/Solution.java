package find_duplicate_subtrees;

/**
 * @author fandeshan
 * @description 652. 寻找重复的子树
 *
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 * 示例 3：
 *
 *
 *
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 *  
 *
 * 提示：
 *
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-duplicate-subtrees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/5
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
    private Map<String,List<TreeNode>> nodeSeenMap = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        List<TreeNode> resNodes = new ArrayList<>();
        for (Map.Entry<String,List<TreeNode>> entry : nodeSeenMap.entrySet()) {
            if (entry.getValue().size() > 1){
                resNodes.add(entry.getValue().get(0));
            }
        }
        return resNodes;
    }
    private String dfs(TreeNode node){
        if (node == null){
            return "";
        }
        StringBuffer sb = new StringBuffer("<");
        sb.append(node.val);
        sb.append("<");
        sb.append(dfs(node.left));
        sb.append(">");
        sb.append("<");
        sb.append(dfs(node.right));
        sb.append(">");
        sb.append(">");
        String result = sb.toString();
        List nodeList = nodeSeenMap.getOrDefault(result,new ArrayList<>());
        nodeList.add(node);
        nodeSeenMap.put(result,nodeList);
        return result;
    }
    private Map<Integer,List<TreeNode>> nodeMap = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        dfs1(root);
        List<TreeNode> result = new ArrayList<>();
        for (Map.Entry<Integer,List<TreeNode>> entry:nodeMap.entrySet()) {
            if (entry.getValue().size()> 1){
                for (int i = 0; i < entry.getValue().size(); i++) {
                    boolean finded = false;
                    for (int j = i+1; j < entry.getValue().size(); j++) {
                        if (checkSame(entry.getValue().get(i), entry.getValue().get(j))){
                            if (!finded){
                                result.add(entry.getValue().get(i));
                            }
                            entry.getValue().remove(j);
                            j = j-1;
                            finded = true;
                        }
                    }
                    if (finded){
                        entry.getValue().remove(i);
                        i --;
                    }
                }
            }
        }
        return result;
    }
    private void dfs1(TreeNode node){
        if (node == null){
            return ;
        }
        List nodeList = nodeMap.getOrDefault(node.val,new ArrayList<>());
        nodeList.add(node);
        nodeMap.put(node.val,nodeList);
        dfs1(node.left);
        dfs1(node.right);
    }
    private boolean checkSame(TreeNode node1,TreeNode node2){
        if (node1 == null && node2 == null){
            return true;
        }
        if (node1== null){
            return false;
        }
        if (node2== null){
            return false;
        }
        return node1.val == node2.val && checkSame(node1.left,node2.left) && checkSame(node1.right,node2.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r11 = new TreeNode(4);
        TreeNode r2 = new TreeNode(3);
        TreeNode r21 = new TreeNode(2);
        TreeNode r22 = new TreeNode(4);
        TreeNode r211 = new TreeNode(4);

        node.left = r1;
        node.right = r2;
        r1.left = r11;
        r2.left = r21;
        r2.right = r22;
        r21.left = r211;

        System.out.println(new Solution().findDuplicateSubtrees(node));
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
