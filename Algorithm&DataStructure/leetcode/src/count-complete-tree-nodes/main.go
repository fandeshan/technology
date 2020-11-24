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
		Val:   1,
		Left:  &TreeNode{
			Val:   2,
			Left:  &TreeNode{
				Val:   4,
				Left:  &TreeNode{
					Val:   8,
					Left:  nil,
					Right: nil,
				},
				Right: &TreeNode{
					Val:   9,
					Left:  nil,
					Right: nil,
				},
			},
			Right: &TreeNode{
				Val:   5,
				Left:  &TreeNode{
					Val:   10,
					Left:  nil,
					Right: nil,
				},
				Right: nil,
			},
		},
		Right: &TreeNode{
			Val:   3,
			Left:  &TreeNode{
				Val:   6,
				Left:  nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val:   7,
				Left:  nil,
				Right: nil,
			},
		},
	}
	fmt.Println(countNodes(root))
	fmt.Println(countNodes1(root))
}

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	leftDeep := getDeep(root.Left)
	rightDeep := getDeep(root.Right)
	if leftDeep == rightDeep {
		return (1 << leftDeep) + countNodes(root.Right)
	} else {
		return (1 << rightDeep) + countNodes(root.Left)
	}
}
func getDeep(node *TreeNode) int  {
	cntH := 0
	for  node != nil {
		cntH ++
		node = node.Left
	}
	return cntH
}

func countNodes1(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return countNodes1(root.Left) + countNodes1(root.Right) +1
}