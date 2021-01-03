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
func partition(head *ListNode, x int) *ListNode {
	dummy := &ListNode{
		-1,head,
	}
	headPre := dummy
	for head != nil {
		if head.Val >= x {
			break
		}
		headPre = head
		head = head.Next
	}
	if head == nil {
		return dummy.Next
	}
	minNodePre := head
	minNode := head.Next
	for minNode != nil {
		if minNode.Val < x {
			minNodePre.Next = minNode.Next
			headPre.Next = minNode
			minNode.Next = head
			headPre = minNode
			minNode = minNodePre.Next
		} else {
			minNodePre = minNode
			minNode = minNode.Next
		}

	}

	return dummy.Next
}