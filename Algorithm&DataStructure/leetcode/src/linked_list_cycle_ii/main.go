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
	head1 := &ListNode{
		Val:  3,
		Next: nil,
	}
	head2 := &ListNode{
		Val:  2,
		Next: nil,
	}
	head3 := &ListNode{
		Val:  0,
		Next: nil,
	}
	head4 := &ListNode{
		Val:  -4,
		Next: nil,
	}
	head1.Next = head2
	head2.Next = head3
	head3.Next = head4
	head4.Next = head2
	newHead := detectCycle(head1)
	newHead.print()
}

func detectCycle(head *ListNode) *ListNode {
	headMap := make(map[*ListNode]int)
	for head != nil {
		if _, ok := headMap[head]; ok {
			return head
		}
		headMap[head] = 1
		head = head.Next
	}
	return nil
}
