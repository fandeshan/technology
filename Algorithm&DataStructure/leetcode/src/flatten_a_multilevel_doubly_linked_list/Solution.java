package flatten_a_multilevel_doubly_linked_list;

/**
 * @author fandeshan
 * @description 430. 扁平化多级双向链表
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *
 *
 * 扁平化后的链表如下图：
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2,null,3]
 * 输出：[1,3,2]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *   1---2---NULL
 *   |
 *   3---NULL
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 如何表示测试用例中的多级链表？
 *
 * 以 示例 1 为例：
 *
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * 序列化其中的每一级之后：
 *
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 *
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 *
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 *  
 *
 * 提示：
 *
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/9/24
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
public class Solution {
    public Node flatten(Node head) {
        dfs(head);
        Node node = head;
        System.out.println(node.val);
        while (node.next != null){
            System.out.println(node.next.val);
            node = node.next;
        }
        System.out.println("=======");
        System.out.println(node.val);
        while (node.prev != null){
            System.out.println(node.prev.val);
            node = node.prev;
        }
        return head;
    }
    private Node dfs(Node node){
        if (node == null){
            return null;
        }
        Node prev = new Node();
        prev.next = node;
        while (node != null ){
            Node next = node.next;
            if (node.child != null){
                node.next = node.child;
                node.child.prev = node;
                Node findNode = dfs(node.child);
                findNode.next = next;
                if (next != null){
                    next.prev = findNode;
                }
                prev = findNode;
                node.child = null;
                node = next;
            } else {
                prev = prev.next;
                node = next;
            }


        }
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
//        head.next = node2;
//        node2.prev = head;
//        node2.next = node3;
//        node3.prev = node2;
//        node3.next = node4;
//        node3.child = node7;
//        node4.prev = node3;
//        node4.next = node5;
//        node5.prev = node4;
//        node5.next = node6;
//        node6.prev = node5;
//        node7.next = node8;
//        node8.prev = node7;
//        node8.next = node9;
//        node8.child = node11;
//        node9.prev = node8;
//        node9.next = node10;
//        node10.prev = node9;
//        node11.next = node12;
//        node12.prev = node11;
        head.child = node2;
        node2.child = node3;
        System.out.println(new Solution().flatten(head));
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    Node(){
    }
    Node(int val){
        this.val = val;
    }
};