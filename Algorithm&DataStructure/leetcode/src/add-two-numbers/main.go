package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

func (l *ListNode) print() {
	p := l
	for p != nil {
		fmt.Print(p.Val)
		fmt.Print("->")
		p = p.Next
	}
	p1 := l
	for p1 != nil {
		fmt.Print(p1.Val)
		fmt.Print("->")
		p1 = p1.Next
	}
}

func main() {
	var l1 *ListNode = &ListNode{
		Val: 9,
		Next: &ListNode{
			Val: 9,
			Next: &ListNode{
				Val: 9,
				Next: &ListNode{
					Val: 9,
					Next: &ListNode{
						Val: 9,
						Next: &ListNode{
							Val:  9,
							Next: nil,
						},
					},
				},
			},
		},
	}
	var l2 *ListNode = &ListNode{
		Val: 9,
		Next: &ListNode{
			Val: 9,
			Next: &ListNode{
				Val: 9,
				Next: &ListNode{
					Val:  9,
					Next: nil,
				},
			},
		},
	}
	result := addTwoNumbers2(l1, l2)
	result.print()

}

func addTwoNumbers1(l1 *ListNode, l2 *ListNode) *ListNode {

	resultArr := []int{}
	addOne := false
	for l1.Next != nil || l2.Next != nil {
		lastP1 := l1
		for lastP1.Next != nil {
			lastP1 = lastP1.Next
		}
		lastP2 := l2
		for lastP2.Next != nil {
			lastP2 = lastP2.Next
		}
		sigResult := lastP1.Val + lastP2.Val
		if addOne {
			sigResult += 1
			addOne = false
		}
		if sigResult > 9 {
			sigResult = sigResult - 10
			addOne = true
		}
		_ = append(resultArr, sigResult)

	}
	var result *ListNode = &ListNode{
		Val:  resultArr[len(resultArr)-1],
		Next: nil,
	}
	for i := len(resultArr) - 2; i >= 0; i-- {
		p := result
		for result.Next == nil {
			p = &ListNode{
				Val:  resultArr[i],
				Next: nil,
			}
			result.Next = p
		}
		p.Next = &ListNode{
			Val:  resultArr[i],
			Next: nil,
		}
	}
	return result
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	l1Arr := []int{}
	for l1 != nil {
		l1Arr = append(l1Arr, l1.Val)
		l1 = l1.Next
	}
	l2Arr := []int{}
	for l2 != nil {
		l2Arr = append(l2Arr, l2.Val)
		l2 = l2.Next
	}

	l1Len := len(l1Arr)
	l2Len := len(l2Arr)
	maxLen := l1Len
	if l2Len > l1Len {
		maxLen = l2Len
	}
	resultArr := []int{}
	addOne := false
	for ; maxLen > 0; maxLen-- {
		l1Num := 0
		l2Num := 0
		if l1Len > 0 {
			l1Num = l1Arr[l1Len-1]
			l1Len--
		}
		if l2Len > 0 {
			l2Num = l2Arr[l2Len-1]
			l2Len--
		}
		sigResult := l1Num + l2Num
		if addOne {
			sigResult += 1
			addOne = false
		}
		if sigResult > 9 {
			sigResult = sigResult - 10
			addOne = true
		}
		resultArr = append(resultArr, sigResult)
	}
	if addOne {
		resultArr = append(resultArr, 1)
	}
	lenRst := len(resultArr)
	if lenRst < 1 {
		return nil
	}

	var result *ListNode = &ListNode{
		Val:  resultArr[lenRst-1],
		Next: nil,
	}
	for i := lenRst - 2; i >= 0; i-- {
		var single = &ListNode{
			Val:  resultArr[i],
			Next: result,
		}
		result = single
	}
	return result
}

func addTwoNumbers2(l1 *ListNode, l2 *ListNode) *ListNode {
	resultArr := []int{}
	addOne := false
	for l1 != nil || l2 != nil {
		l1Num := 0
		l2Num := 0
		if l1 != nil {
			l1Num = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			l2Num = l2.Val
			l2 = l2.Next
		}
		sigResult := l1Num + l2Num
		if addOne {
			sigResult += 1
			addOne = false
		}
		if sigResult > 9 {
			sigResult = sigResult - 10
			addOne = true
		}
		resultArr = append(resultArr, sigResult)
	}
	if addOne {
		resultArr = append(resultArr, 1)
	}
	lenRst := len(resultArr)
	if lenRst < 1 {
		return nil
	}

	var result *ListNode = &ListNode{
		Val:  resultArr[lenRst-1],
		Next: nil,
	}
	for i := lenRst - 2; i >= 0; i-- {
		var single = &ListNode{
			Val:  resultArr[i],
			Next: result,
		}
		result = single
	}
	return result
}
