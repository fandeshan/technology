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
	fmt.Print(node.Val)
	fmt.Print("-->")
	node.Next.print()
}

func main() {
	head := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val: 3,
				Next: &ListNode{
					Val:  4,
					Next: nil,
				},
			},
		},
	}
	newHead := swapPairs(head)
	newHead.print()
}

func swapPairs(head *ListNode) *ListNode {
	dummy := &ListNode{
		Val:  -1,
		Next: head,
	}
	A0 := dummy
	A1 := dummy.Next
	if A1 == nil || A1.Next == nil {
		return A1
	}
	A2 := dummy.Next.Next
	for A1 != nil || A2 != nil {
		A0.Next = A2
		A1.Next = A2.Next
		A2.Next = A1
		A0 = A1
		A1 = A1.Next
		if A1 == nil {
			break
		}
		A2 = A1.Next
	}
	return dummy.Next
}
