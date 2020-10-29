package main

import (
	"fmt"
	"math"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	root := &TreeNode{
		Val: 8,
		Left: &TreeNode{
			Val: 3,
			Left: &TreeNode{
				Val:   1,
				Left:  nil,
				Right: nil,
			},
			Right: &TreeNode{
				Val:   6,
				Left:  nil,
				Right: nil,
			},
		},
		Right: &TreeNode{
			Val:   9,
			Left:  nil,
			Right: nil,
		},
	}
	fmt.Println(getMinimumDifference(root))
}

func getMinimumDifference(root *TreeNode) int {
	minVal := math.MaxInt32
	arr := []int{}
	dfs(root, &arr)
	if len(arr) < 2 {
		return minVal
	}
	for i, j := 0, 1; j < len(arr); {
		if arr[j]-arr[i] < minVal {
			minVal = arr[j] - arr[i]
		}
		i++
		j++
	}
	return minVal
}
func dfs(root *TreeNode, arr *[]int) {
	if root == nil {
		return
	}
	dfs(root.Left, arr)
	*arr = append(*arr, root.Val)
	dfs(root.Right, arr)
}
