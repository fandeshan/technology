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
func reversePrint1(head *ListNode) []int {
	if head == nil {
		return nil
	}
	result := make([]int,0)
	for head != nil  {
		result = append([]int{head.Val},result...)
		head = head.Next
	}
	return result
}
func reversePrint(head *ListNode) []int {
	if head == nil {
		return nil
	}
	node := head
	len := 0
	for node != nil  {
		len ++
		node = node.Next
	}
	result := make([]int,len)
	cnt := len -1
	for head != nil  {
		result[cnt] = head.Val
		cnt --
		head = head.Next
	}
	return result
}