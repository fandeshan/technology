package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	//root := &TreeNode{
	//	Val:   4,
	//	Left:  &TreeNode{
	//		Val:   9,
	//		Left:  &TreeNode{
	//			Val:   5,
	//			Left:  nil,
	//			Right: nil,
	//		},
	//		Right: &TreeNode{
	//			Val:   1,
	//			Left:  nil,
	//			Right: nil,
	//		},
	//	},
	//	Right: &TreeNode{
	//		Val:   0,
	//		Left:  nil,
	//		Right: nil,
	//	},
	//}

	//root := &TreeNode{
	//	Val:   1,
	//	Left:  &TreeNode{
	//		Val:   2,
	//		Left:  nil,
	//		Right: nil,
	//	},
	//	Right: &TreeNode{
	//		Val:   3,
	//		Left:  nil,
	//		Right: nil,
	//	},
	//}
	root := &TreeNode{
		Val:   1,
		Left:  nil,
		Right: nil,
	}
	fmt.Println(sumNumbers(root))
}

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumNumbers(root *TreeNode) int {
	return traverseTree(root, Stack{})
}
func traverseTree(root *TreeNode, stack Stack) int {
	if root == nil {
		return 0
	}
	sum := 0
	stack.Push(root.Val)
	if root.Left == nil && root.Right == nil {
		tmp := stack
		muti := 1
		num := 0
		for !tmp.IsEmpty() {
			num = num + tmp.Pop().(int)*muti
			muti = muti * 10
		}
		return sum + num
	}
	sum = sum + traverseTree(root.Left, stack)
	sum = sum + traverseTree(root.Right, stack)
	stack.Pop()
	return sum
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
