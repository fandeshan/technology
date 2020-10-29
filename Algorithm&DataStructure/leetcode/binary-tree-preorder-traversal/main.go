package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	root := &TreeNode{
		Val:  1,
		Left: nil,
		Right: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val:   3,
				Left:  nil,
				Right: nil,
			},
			Right: nil,
		},
	}
	fmt.Println(preorderTraversal(root))
}
func preorderTraversal(root *TreeNode) []int {
	result := []int{}
	if root == nil {
		return result
	}
	p1 := root
	var p2 *TreeNode
	for p1 != nil {
		p2 = p1.Left
		if p2 != nil {
			for p2.Right != nil && p2.Right != p1 {
				p2 = p2.Right
			}
			if p2.Right == nil {
				result = append(result, p1.Val)
				p2.Right = p1
				p1 = p1.Left
				continue
			} else {
				p2.Right = nil
			}
		} else {
			result = append(result, p1.Val)
		}
		p1 = p1.Right
	}
	return result
}
func preorderTraversal1(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	result := []int{root.Val}
	result = append(result, preorderTraversal(root.Left)...)
	result = append(result, preorderTraversal(root.Right)...)
	return result
}
