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
	node := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val: 3,
				Next: &ListNode{
					Val: 4,
					Next: &ListNode{
						Val:  5,
						Next: nil,
					},
				},
			},
		},
	}
	node.print()
	fmt.Println()
	reorderList(node)
	node.print()
}

type Stack []interface{}

func (q *Stack) Push(v interface{}) {
	*q = append(*q, v)
}
func (q *Stack) Pop() interface{} {
	head := (*q)[len(*q)-1]
	*q = (*q)[:len(*q)-1]
	return head
}
func (q *Stack) IsEmpty() bool {
	return len(*q) == 0
}

func reorderList(head *ListNode) {
	if head == nil || head.Next == nil {
		return
	}
	halfLeft := Stack{}
	for head != nil {
		halfLeft.Push(head)
		head = head.Next
	}
	lenList := len(halfLeft)
	halfRight := Stack{}
	for i := 0; i < lenList/2; i++ {
		halfRight.Push(halfLeft.Pop())
	}

	if lenList%2 != 0 {
		head = halfLeft.Pop().(*ListNode)
		head.Next = nil
	}
	for i := 0; i < lenList/2; i++ {
		leftNode := halfLeft.Pop()
		rightNode := halfRight.Pop()
		rightNode.(*ListNode).Next = head
		leftNode.(*ListNode).Next = rightNode.(*ListNode)
		head = leftNode.(*ListNode)
	}
}
