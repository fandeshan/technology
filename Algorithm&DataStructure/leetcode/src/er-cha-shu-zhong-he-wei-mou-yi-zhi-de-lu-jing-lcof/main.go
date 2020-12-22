package main

func main() {

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
func pathSum(root *TreeNode, sum int) [][]int {
	if root == nil {
		return nil
	}
	if root.Left == nil && root.Right == nil {
		if root.Val == sum {
			return [][]int{{root.Val}}
		}
		return nil
	}
	leftArr := pathSum(root.Left,sum-root.Val)
	rightArr := pathSum(root.Right,sum-root.Val)
	result := make([][]int,0)
	for i := 0 ; i < len(leftArr) ; i ++ {
		result = append(result,append([]int{root.Val},leftArr[i]...))
	}
	for i := 0 ; i < len(rightArr) ; i ++ {
		result = append(result,append([]int{root.Val},rightArr[i]...))
	}
	return result
}