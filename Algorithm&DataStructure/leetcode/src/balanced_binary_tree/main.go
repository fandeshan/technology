package main

import (
	"fmt"
	"math"
)

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}
func main() {
	//root := &TreeNode{
	//	Val:   1,
	//	Left:  &TreeNode{
	//		Val:   2,
	//		Left:  &TreeNode{
	//			Val:   3,
	//			Left:  &TreeNode{
	//				Val:   4,
	//				Left:  nil,
	//				Right: nil,
	//			},
	//			Right: &TreeNode{
	//				Val:   4,
	//				Left:  nil,
	//				Right: nil,
	//			},
	//		},
	//		Right: &TreeNode{
	//			Val:   3,
	//			Left:  nil,
	//			Right: nil,
	//		},
	//	},
	//	Right: &TreeNode{
	//		Val:   2,
	//		Left:  nil,
	//		Right: nil,
	//	},
	//}

	root := &TreeNode{
		Val:   1,
		Left:  nil,
		Right: nil,
	}
	fmt.Println(isBalanced(root))
}

func isBalanced(root *TreeNode) bool {

	return deepTree(root)>=0
}
//func deepTree(root *TreeNode,deep int)  {
//	if root == nil {
//		return
//	}
//	if root.Left == nil && root.Right == nil {
//		if deep > max {
//			max = deep
//		}
//		if deep < min {
//			min = deep
//		}
//		return
//	}
//	deepTree(root.Left,deep+1)
//	deepTree(root.Right,deep+1)
//}

func deepTree(root *TreeNode) int{
	if root == nil {
		return 0
	}
	leftDeep := deepTree(root.Left)
	rightDeep := deepTree(root.Right)
	if leftDeep == -1 || rightDeep == -1 || math.Abs(float64(leftDeep - rightDeep)) > 1 {
		return -1;
	} else {
		return int(math.Max(float64(leftDeep),float64(rightDeep))) + 1;
	}

}

