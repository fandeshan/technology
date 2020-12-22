package main

import "fmt"

func main() {
	root := &TreeNode{
		Val: 3,
		Left: &TreeNode{
			Val: 9,
			Left: nil,
			Right: nil,
		},
		Right: &TreeNode{
			Val: 20,
			Left: &TreeNode{
				Val: 15,
				Left: nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val: 7,
				Left: nil,
				Right: nil,
			},
		},
	}
	fmt.Println(zigzagLevelOrder(root))
}

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func zigzagLevelOrder(root *TreeNode) [][]int {
	if root == nil {
		return nil
	}
	result := make([][]int,0)

	stack := []*TreeNode{root}
	left := true
	for len(stack) > 0 {
		n := len(stack)
		newStack := []*TreeNode{}
		tmp := []int{}
		for i := n-1;i >=0 ;i -- {
			tmp = append(tmp,stack[i].Val)
			if left {
				if stack[i].Left != nil {
					newStack = append(newStack,stack[i].Left)
				}
				if stack[i].Right != nil {
					newStack = append(newStack,stack[i].Right)
				}
			} else {
				if stack[i].Right != nil {
					newStack = append(newStack,stack[i].Right)
				}
				if stack[i].Left != nil {
					newStack = append(newStack,stack[i].Left)
				}
			}
		}
		result = append(result,tmp)
		left = !left
		stack = newStack
	}
	return result
}