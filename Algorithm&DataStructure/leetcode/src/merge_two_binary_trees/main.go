package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func (l *TreeNode) print() {
	p := l
	if p == nil {
		return
	}
	fmt.Print(p.Val)
	p.Left.print()
	p.Right.print()

}
func main() {
	t1 := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 3,
			Left: &TreeNode{
				Val:   5,
				Left:  nil,
				Right: nil,
			},
			Right: nil,
		},
		Right: &TreeNode{
			Val:   2,
			Left:  nil,
			Right: nil,
		},
	}
	t2 := &TreeNode{
		Val: 2,
		Left: &TreeNode{
			Val:  1,
			Left: nil,
			Right: &TreeNode{
				Val:   4,
				Left:  nil,
				Right: nil,
			},
		},
		Right: &TreeNode{
			Val:  3,
			Left: nil,
			Right: &TreeNode{
				Val:   7,
				Left:  nil,
				Right: nil,
			},
		},
	}
	result := mergeTrees(t1, t2)
	result.print()
}

/**
 * Definition for a binary tree node.
 *
 */
func mergeTrees(t1 *TreeNode, t2 *TreeNode) *TreeNode {
	if t1 == nil {
		return t2
	}
	if t2 == nil {
		return t1
	}
	result := &(*t1)
	result.Val = result.Val + t2.Val
	result.Left = mergeTrees(result.Left, t2.Left)
	result.Right = mergeTrees(result.Right, t2.Right)
	return result
}
