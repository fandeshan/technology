package main

import "math"

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

func main() {
	
}

func isBalanced(root *TreeNode) bool {

	return deepTree(root)>=0
}


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