package main

import (
	"fmt"
)

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}
func main() {
	root := &TreeNode{
		Val:   3,
		Left:  &TreeNode{
			Val:   2,
			Left:  nil,
			Right: &TreeNode{
				Val:   3,
				Left:  nil,
				Right: nil,
			},
		},
		Right: &TreeNode{
			Val:   3,
			Left:  nil,
			Right: &TreeNode{
				Val:   1,
				Left:  nil,
				Right: nil,
			},
		},
	}
	fmt.Println(rob(root))
	root2 := &TreeNode{
		Val:   3,
		Left:  &TreeNode{
			Val:   4,
			Left:  &TreeNode{
				Val:   1,
				Left:  nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val:   3,
				Left:  nil,
				Right: nil,
			},
		},
		Right: &TreeNode{
			Val:   5,
			Left:  nil,
			Right: &TreeNode{
				Val:   1,
				Left:  nil,
				Right: nil,
			},
		},
	}
	fmt.Println(rob(root2))
}

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rob(root *TreeNode) int {
	robedMap := make(map[*TreeNode]int)
	return maxAmount(root,robedMap)
}
func maxAmount(root *TreeNode,robedMap map[*TreeNode]int) int {
	if root == nil {
		return 0
	}
	if maxVal,ok := robedMap[root];ok{
		return maxVal
	}
	maxVal := 0
	if root.Left == nil && root.Right == nil {
		maxVal = root.Val
	} else if root.Left == nil {
		maxVal = max(root.Val +
			maxAmount(root.Right.Left,robedMap) +
			maxAmount(root.Right.Right,robedMap),
			maxAmount(root.Right,robedMap))
	} else if root.Right == nil {
		maxVal = max(root.Val +
			maxAmount(root.Left.Left,robedMap) +
			maxAmount(root.Left.Right,robedMap),
			maxAmount(root.Left,robedMap))
	} else {
		maxVal = max(root.Val +
			maxAmount(root.Left.Left,robedMap) +
			maxAmount(root.Left.Right,robedMap) +
			maxAmount(root.Right.Left,robedMap) +
			maxAmount(root.Right.Right,robedMap),
			maxAmount(root.Left,robedMap) +
			maxAmount(root.Right,robedMap))
	}
	robedMap[root] = maxVal
	return maxVal
}


func max(a ...int) int {
	maxVal := a[0]
	for i := 1;i < len(a) ; i++  {
		if maxVal < a[i] {
			maxVal = a[i]
		}
	}
	return maxVal
}