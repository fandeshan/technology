package complete_binary_tree_inserter;

/**
 * @author fandeshan
 * @description 919. 完全二叉树插入器
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *  
 *
 * 提示：
 *
 * 树中节点数量范围为 [1, 1000] 
 * 0 <= Node.val <= 5000
 * root 是完全二叉树
 * 0 <= val <= 5000 
 * 每个测试用例最多调用 insert 和 get_root 操作 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/complete-binary-tree-inserter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/7/25
 */

import java.util.*;

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

public class CBTInserter {
    TreeNode root;
//    int size;
//    Map<TreeNode,TreeNode> parentMap;
//    TreeNode currNode;
    public CBTInserter(TreeNode root) {
        this.root = root;
//        this.size = 0;
//        this.parentMap = new HashMap<>();
//        if (root == null){
//            return ;
//        }
//        this.parentMap.put(root,null);
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            int len = queue.size();
//            for (int i = 0; i < len; i++) {
//                TreeNode node = queue.poll();
//                if (node.left != null){
//                    this.size ++;
//                    queue.offer(node.left);
//                    this.parentMap.put(node.left,node);
//                }
//                if (node.right != null){
//                    this.size ++;
//                    queue.offer(node.right);
//                    this.parentMap.put(node.right,node);
//                }
//                currNode = node;
//            }
//        }
    }

    public int insert(int val) {
        TreeNode newNode = new TreeNode(val);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int n = 1;
        List<TreeNode> parentNodes = new ArrayList<>();
        int index = 0;
        boolean hasSet =false;
        while (!queue.isEmpty()){
            if (n == queue.size()){
                parentNodes = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    parentNodes.add(node);
                    if (node.left != null){
                        queue.offer(node.left);
                    }
                    if (node.right != null){
                        queue.offer(node.right);
                    }
                }
            } else {
                hasSet = true;
                index = queue.size()/2;
                if (queue.size()%2==0){
                    parentNodes.get(index).left =newNode;
                } else {
                    parentNodes.get(index).right =newNode;
                }
                break;
            }
            n = n << 1;
        }
        if (!hasSet){
            TreeNode node = root;
            while (node.left !=null){
                node = node.left;
            }
            node.left = newNode;
        }
        return parentNodes.get(index).val;



//        if (currNode == null){
//            return -1;
//        }
//        TreeNode parentNode = this.parentMap.get(currNode);
//        if (parentNode == null){
//            currNode.left = newNode;
//        }
//        if (parentNode.right == null){
//            parentNode.right = newNode;
//            this.parentMap.put(parentNode.right,parentNode);
//        }
//        this.size ++;
//        currNode = newNode;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        root.left = r1;
        CBTInserter cBTInserter = new CBTInserter(root);
        System.out.println(cBTInserter.insert(3));
        System.out.println(cBTInserter.insert(4));
        cBTInserter.get_root(); // 返回 [1, 2, 3, 4]

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
/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
