package main

import "fmt"

func main() {
	//fmt.Println(partitionLabels("ababcbacadefegdehijhklij"))
	fmt.Println(partitionLabels("ab"))
}

var mapLen int = 26

func partitionLabels(S string) []int {
	if len(S) < 2 {
		return []int{len(S)}
	}
	myMap := make([]*ListNode, mapLen)
	sArr := []byte(S)
	result := []int{}
	for i := 0; i < len(sArr); i++ {
		node := myMap[int(sArr[i])%mapLen]
		if node == nil {
			node = &ListNode{
				Val:  i,
				Next: nil,
			}
		} else {
			next := node
			for next.Next != nil {
				next = next.Next
			}
			next.Next = &ListNode{
				Val:  i,
				Next: nil,
			}
		}
		myMap[int(sArr[i])%mapLen] = node
	}
	maxPos := 0
	lastIndex := -1
	for i := 0; i < len(sArr); i++ {
		node := myMap[int(sArr[i])%mapLen]
		for node != nil {
			if node.Val > maxPos {
				maxPos = node.Val
			}
			node = node.Next
		}
		if i == maxPos {
			result = append(result, i-lastIndex)
			lastIndex = i
		}
	}
	return result
}

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
