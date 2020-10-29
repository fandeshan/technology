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
	newNode := reverseBetween(node, 2, 4)
	newNode.print()
	fmt.Println()
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseBetween(head *ListNode, m int, n int) *ListNode {
	if head == nil || m >= n {
		return head
	}
	//构造前一个节点，方便操作
	preHead := &ListNode{
		Val:  -1,
		Next: head,
	}
	head = preHead
	for i := 0; i < m-1 || head == nil; i++ {
		head = head.Next
	}
	// 如果m比node总数还大，直接返回
	if head == nil {
		return head
	}
	//记录交换区间m的前一个node
	prevM := head
	//m开始交换的node
	mNode := head.Next
	if mNode == nil {
		return head
	}
	//n 的node，初始和m一样
	nNode := mNode
	//n的操作值，相当于curr
	postN := nNode.Next
	for i := m; i < n; i++ {
		next := postN.Next
		postN.Next = nNode
		nNode = postN
		postN = next
	}
	//最后让m 的node的next指向新操作的n
	mNode.Next = postN
	//再让m的前一个node指向最终到n节点的node，这两部操作相当于 m的pre指向n的curr，m的node指向n的next
	prevM.Next = nNode
	return preHead.Next
}
