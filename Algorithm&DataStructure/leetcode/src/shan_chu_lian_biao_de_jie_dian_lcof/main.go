package main

func main() {

}
type ListNode struct {
	Val int
	Next *ListNode
}
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNode(head *ListNode, val int) *ListNode {
	if head == nil {
		return nil
	}
	dummy := &ListNode{
		-1,head,
	}
	preHead := dummy
	for  head.Val != val {
		head = head.Next
		preHead = preHead.Next
		if head == nil {
			return dummy.Next
		}
	}
	preHead.Next = head.Next
	return dummy.Next
}