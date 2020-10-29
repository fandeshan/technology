package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func (node *ListNode) print() {
	if node == nil {
		return
	}
	fmt.Printf("%d-->", node.Val)
	node.Next.print()
}

func main() {
	//head := &ListNode{
	//	Val:  1,
	//	Next: &ListNode{
	//		Val:  2,
	//		Next: &ListNode{
	//			Val:  2,
	//			Next: &ListNode{
	//				Val:  1,
	//				Next: nil,
	//			},
	//		},
	//	},
	//}
	head := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val:  1,
				Next: nil,
			},
		},
	}
	fmt.Println(isPalindrome(head))
}

func isPalindrome(head *ListNode) bool {
	if head == nil {
		return true
	}

	slow := head
	fast := head

	for fast != nil {
		if fast.Next == nil {
			break
		}
		fast = fast.Next.Next
		slow = slow.Next
	}

	prev := slow
	curr := slow.Next
	prev.Next = nil
	var next *ListNode
	for curr != nil {
		next = curr.Next
		curr.Next = prev
		prev = curr
		curr = next
	}
	for prev != nil {
		if prev.Val != head.Val {
			return false
		}
		prev = prev.Next
		head = head.Next
	}
	return true
}
