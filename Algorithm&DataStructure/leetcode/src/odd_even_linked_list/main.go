package main

import "fmt"

type ListNode struct {
	Val int
	Next *ListNode
}

func (node *ListNode) print()  {
	if node == nil {
		return
	}
	fmt.Print(node.Val,"->")
	node.Next.print()
}
func main() {
	head := &ListNode{
		Val:  1,
		Next: &ListNode{
			Val:  2,
			Next: &ListNode{
				Val:  3,
				Next: &ListNode{
					Val:  4,
					Next: &ListNode{
						Val:  5,
						Next: &ListNode{
							Val:  6,
							Next: &ListNode{
								Val:  7,
								Next: nil,
							},
						},
					},
				},
			},
		},
	}
	head1 := &ListNode{
		Val:  1,
		Next: &ListNode{
			Val:  2,
			Next: &ListNode{
				Val:  3,
				Next: &ListNode{
					Val:  4,
					Next: &ListNode{
						Val:  5,
						Next: nil,
					},
				},
			},
		},
	}
	oddEvenList(head)
	head.print()
	fmt.Println()
	oddEvenList(head1)
	head1.print()
	fmt.Println()
	head3 := &ListNode{
		Val:  1,
		Next: &ListNode{
			Val:  2,
			Next: nil,
		},
	}
	oddEvenList(head3)
	head3.print()
	fmt.Println()
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

func oddEvenList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	odd1 := head
	odd2Prev := head.Next
	odd2 := head.Next.Next
	i := 1
	for odd2 != nil  {
		odd2Prev.Next = odd2.Next
		odd2.Next = odd1.Next
		odd1.Next = odd2
		odd1 = odd2
		odd2Prev = odd2.Next
		for j:=0;j<i ;j++  {
			odd2Prev = odd2Prev.Next
			if odd2Prev == nil {
				return head
			}
		}
		i++
		odd2 = odd2Prev.Next

	}
	return head
}