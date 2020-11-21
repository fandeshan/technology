package main

import (
	"fmt"
	"time"
)

type ListNode struct {
	Val int
	Next *ListNode
}

func (this *ListNode) print()  {
	if this == nil {
		fmt.Println()
		return
	}
	fmt.Print(this.Val,"->")
	this.Next.print()
}

func main() {
	//head := &ListNode{
	//	Val:  -1,
	//	Next: &ListNode{
	//		Val:  5,
	//		Next: &ListNode{
	//			Val:  3,
	//			Next: &ListNode{
	//				Val:  4,
	//				Next: &ListNode{
	//					Val:  0,
	//					Next: nil,
	//				},
	//			},
	//		},
	//	},
	//}
	//node := head
	//nodeArr := []*ListNode{}
	//for node != nil  {
	//	nodeArr = append(nodeArr,node)
	//	node = node.Next
	//}
	//sortList(head)
	//for i := 0; i < len(nodeArr) ; i ++  {
	//	nodeArr[i].print()
	//}
	//head.print()
	node2 := &ListNode{
		Val:  2,
		Next: nil,
	}
	curr := node2
	for i:=3;i<=50000 ;i++  {
		tmp := &ListNode{
			Val:  i,
			Next: nil,
		}
		curr.Next = tmp
		curr = tmp
	}
	curr.Next = &ListNode{
		Val:  1,
		Next: nil,
	}

	node := node2
	nodeArr := []*ListNode{}
	for node != nil  {
		nodeArr = append(nodeArr,node)
		node = node.Next
	}
	t1 := time.Now()
	result := sortList(node2)
	currRst := result
	for i:=1;i<=50000 ;i++  {
		if currRst.Val != i {
			fmt.Print(i," ")
			continue
		}
		currRst = currRst.Next
	}
	fmt.Println()
	result.print()
	fmt.Println(time.Since(t1))

	for i := 0; i < len(nodeArr) ; i ++  {
		nodeArr[i].print()
	}
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func sortList(head *ListNode) *ListNode {
	dummy := &ListNode{
		Val:  -1,
		Next: head,
	}
	quickSortNode(dummy)
	return dummy.Next
}
func quickSortNode(dummy *ListNode){
	node := dummy.Next
	if node == nil || node.Next == nil {
		return
	}
	cnt := 0
	curr := node
	for curr != nil  {
		curr = curr.Next
		cnt ++
	}
	leftOrginNode := node
	righOrgintNode := node
	righOrgintNodePrefix := &ListNode{
		Val:  -1,
		Next: node,
	}
	for i := 0; i < cnt/2 ;i++  {
		righOrgintNodePrefix = righOrgintNode
		righOrgintNode = righOrgintNode.Next

	}
	righOrgintNodePrefix.Next = nil
	base := leftOrginNode.Val
	leftNode := leftOrginNode.Next
	midNode := leftOrginNode
	leftOrginNode.Next = nil
	leftNodePrefix := &ListNode{
		Val:  -1,
		Next: leftNode,
	}
	leftTopNode := leftNodePrefix

	rightNode := righOrgintNode
	rightNodePrefix := &ListNode{
		Val:  -1,
		Next: righOrgintNode,
	}
	rightTopNode := rightNodePrefix
	//fmt.Println("+++++++++++++++++++++++++")
	//leftTopNode.print()
	//midNode.print()
	//rightTopNode.print()
	//fmt.Println("&&&&&&&&&&&&&&&&&&&&&&&&&")
	firstInsert := true
	for leftNode != nil  {
		leftNodeNext := leftNode.Next
		if leftNode.Val > base {
			leftNodePrefix.Next = leftNodeNext
			rightTopNodeNext := rightTopNode.Next
			rightTopNode.Next = leftNode
			if firstInsert {
				rightNodePrefix = leftNode
				firstInsert = false
			}
			leftNode.Next = rightTopNodeNext
		}else{
			leftNodePrefix = leftNode
		}
		leftNode = leftNodeNext
	}
	for rightNode != nil  {
		rightNodeNext := rightNode.Next
		if rightNode.Val < base {
			rightNodePrefix.Next = rightNodeNext
			leftTopNodeNext := leftTopNode.Next
			leftTopNode.Next = rightNode
			rightNode.Next = leftTopNodeNext
		} else {
			rightNodePrefix = rightNode
		}
		rightNode = rightNodeNext
	}
	//fmt.Println("+++++++++++++++++")
	//leftTopNode.print()
	//midNode.print()
	//rightTopNode.print()
	//fmt.Println("&&&&&&&&&&&&&&&&&")
	quickSortNode(leftTopNode)
	quickSortNode(rightTopNode)
	//fmt.Println("-----------------")
	//leftTopNode.print()
	//midNode.print()
	//rightTopNode.print()
	//fmt.Println("=================")
	curr = leftTopNode
	for curr.Next != nil  {
		curr = curr.Next
	}
	curr.Next = midNode
	midNode.Next = rightTopNode.Next
	dummy.Next = leftTopNode.Next
}