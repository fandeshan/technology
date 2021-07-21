package liang_ge_lian_biao_de_di_yi_ge_gong_gong_jie_dian_lcof;

/**
 * @author fandeshan
 * @description 剑指 Offer 52. 两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 *
 * 示例 2：
 *
 *
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 *
 * 示例 3：
 *
 *
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *  解题思路1
 *   map或者set存储其中一个链表各个节点，再遍历另一个链表查询map是否有存在
 *
 *  解体思路2
 *
 *     \
 *      \  a
 *       \
 *        \   c
 *         --------
 *       / b
 *      /
 *     /
 *   a为链表A的未交叉距离，b为链表B 的未交叉距离，c为A和B链表交叉距离
 *
 *   一个遍历从先走完A链表，再走到B链表的b距离 总长度为 a+c+b
 *   另一个遍历先走完B链表，再走到A链表的b距离 总长度为 b+c+a
 *   这两个遍历同时进行 则 a+c+b = b+c+a，则会走到一起
 *   如果没有公共交叉
 *   则c=0，同样，也是 a+b+0=b+a+0，
 *
 * @date 2021/7/21
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode h1 = headA;
        ListNode h2 = headB;
        while (h1 != h2){
            h1 = h1 == null ? headB:h1.next;
            h2 = h2 == null ? headA:h2.next;
        }
        return h1;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Map<ListNode,Integer> repeatMap = new HashMap<>();

        while (headA != null ) {
            repeatMap.put(headA,1);
            headA = headA.next;
        }
        while (headB != null){
            if (repeatMap.containsKey(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode A1 = new ListNode(4);
        ListNode A2 = new ListNode(1);
        ListNode A3 = new ListNode(8);
        ListNode A4 = new ListNode(4);
        ListNode A5 = new ListNode(5);
        A1.next = A2;
        A2.next = A3;
        A3.next = A4;
        A4.next = A5;
        ListNode B1 = new ListNode(5);
        ListNode B2 = new ListNode(0);
        ListNode B3 = new ListNode(1);
        B1.next = B2;
        B2.next = B3;
        B3.next = A3;

        System.out.println(new Solution().getIntersectionNode(A1,B1));
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}