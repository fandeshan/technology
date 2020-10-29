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
	fmt.Println(postorderTraversal(root))
}

func postorderTraversal(root *TreeNode) []int {
	result := make([]int, 0)
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
				p2.Right = p1
				p1 = p1.Left
				continue
			} else {
				p2.Right = nil
				addArr(p1.Left, &result)
			}
		}
		p1 = p1.Right
	}
	addArr(root, &result)
	return result
}

func addArr(root *TreeNode, nums *[]int) {
	tail := reverseTree(root)
	cur := tail
	for cur != nil {
		*nums = append(*nums, cur.Val)
		cur = cur.Right
	}
	reverseTree(tail)
}

func reverseTree(root *TreeNode) *TreeNode {
	var pre *TreeNode
	var next *TreeNode
	for root != nil {
		next = root.Right
		root.Right = pre
		pre = root
		root = next
	}
	return pre
}

func postorderTraversal1(root *TreeNode) []int {
	if root == nil {
		return make([]int, 0)
	}
	result := make([]int, 0)
	result = append(result, postorderTraversal1(root.Left)...)
	result = append(result, postorderTraversal1(root.Right)...)
	result = append(result, root.Val)
	return result
}
