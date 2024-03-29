package print_binary_tree;

/**
 * @author fandeshan
 * @description 655. 输出二叉树
 *
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 *
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 *
 *  
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2]
 * 输出：
 * [["","1",""],
 *  ["2","",""]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3,null,4]
 * 输出：
 * [["","","","1","","",""],
 *  ["","2","","","","3",""],
 *  ["","","4","","","",""]]
 *  
 *
 * 提示：
 *
 * 树中节点数在范围 [1, 210] 内
 * -99 <= Node.val <= 99
 * 树的深度在范围 [1, 10] 内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/print-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/22
 */

import java.util.ArrayList;
import java.util.List;

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
    int maxDeep;
    int maxSize;
    public List<List<String>> printTree(TreeNode root) {
        getDeep(root,1,1);
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < maxDeep; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < maxSize; j++) {
                temp.add("");
            }
            result.add(temp);
        }
        dfs(root,maxSize/2,0,result);
        return result;
    }

    private void getDeep(TreeNode node,int deep,int size){
        if (node == null){
            return ;
        }
        this.maxDeep = Math.max(deep,this.maxDeep);
        this.maxSize = Math.max(size,this.maxSize);
        getDeep(node.left,deep+1,size*2+1);
        getDeep(node.right,deep+1,size*2+1);
    }

    private void dfs(TreeNode node,int pos,int deep,List<List<String>> result){
        if (node == null){
            return ;
        }
        result.get(deep).set(pos,Integer.toString(node.val));
        dfs(node.left,pos-((int) Math.pow(2,this.maxDeep-deep-1)/2),deep+1,result);
        dfs(node.right,pos+((int) Math.pow(2,this.maxDeep-deep-1)/2),deep+1,result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(3);
        TreeNode r12 = new TreeNode(4);
        TreeNode r122 = new TreeNode(5);
        root.left = r1;
        root.right = r2;
        r1.right = r12;
        r12.right = r122;
        System.out.println(new Solution().printTree(root));
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
