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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

public class CBTInserter1 {

    Queue<TreeNode> candidate;
    TreeNode root;

    public CBTInserter1(TreeNode root) {
        this.root = root;
        this.candidate = new ArrayDeque<TreeNode>();
        this.root = root;

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            //把所有 左右节点不完整的node 加入到candidate队列
            if (!(node.left != null && node.right != null)) {
                candidate.offer(node);
            }
        }

    }

    public int insert(int val) {
        TreeNode child = new TreeNode(val);
        TreeNode node = candidate.peek();
        int ret = node.val;
        if (node.left == null) {
            node.left = child;
        } else {
            node.right = child;
            //如果设置了右节点，则出队列
            candidate.poll();
        }
        //当前加入的节点入队
        candidate.offer(child);
        return ret;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        root.left = r1;
        CBTInserter1 cBTInserter = new CBTInserter1(root);
        System.out.println(cBTInserter.insert(3));
        System.out.println(cBTInserter.insert(4));
        cBTInserter.get_root(); // 返回 [1, 2, 3, 4]

    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
