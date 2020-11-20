package main
type ListNode struct {
	Val int
	Next *ListNode
}
func main() {
	
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func insertionSortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummy := &ListNode{
		Val:  -1,
		Next: head,
	}
	curr := head.Next
	currPrefix := head
	for curr != nil  {
		currNext := curr.Next
		travel := dummy
		for travel.Next.Val <= curr.Val && travel.Next != curr  {
			travel = travel.Next
		}
		if travel.Next != curr {
			travelNext := travel.Next
			travel.Next = curr
			curr.Next = travelNext
			currPrefix.Next = currNext
		}else{
			currPrefix = curr
		}

		curr = currNext
	}
	return dummy.Next
}