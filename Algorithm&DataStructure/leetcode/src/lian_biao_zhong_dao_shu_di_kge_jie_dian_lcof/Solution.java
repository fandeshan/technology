package lian_biao_zhong_dao_shu_di_kge_jie_dian_lcof;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2021/9/2
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode rightNode = head;
        for (int i = 0; i < k; i++) {
            if (rightNode == null){
                return rightNode;
            }
            rightNode = rightNode.next;
        }
        if (rightNode == null){
            return rightNode;
        }
        ListNode leftNode = head;
        while (rightNode != null){
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        return leftNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(new Solution().getKthFromEnd(node1,2).val);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
