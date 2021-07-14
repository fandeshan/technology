package main

type ListNode struct {
	Val  int
	Next *ListNode
}

func main() {

}

func reverseList(head *ListNode) *ListNode {
	if head == nil {
		return head
	}
	prev := head
	curr := head.Next
	prev.Next = nil
	var next *ListNode
	for curr != nil {
		next = curr.Next
		curr.Next = prev
		prev = curr
		curr = next
	}
	return prev
}
