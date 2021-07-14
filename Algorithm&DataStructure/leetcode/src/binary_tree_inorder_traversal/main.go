package main
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
	
}
func inorderTraversal(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	result :=make([]int,0)
	stack := make([]*TreeNode,0)
	current := root
	for current != nil || len(stack) > 0  {
		for current != nil  {
			stack = append(stack,current)
			current = current.Left
		}
		current = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		result = append(result,current.Val)
		current = current.Right

	}
	return result
}

func inorderTraversal1(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	result :=[]int{}
	result = append(result,inorderTraversal1(root.Left)...)
	result = append(result,root.Val)
	result = append(result,inorderTraversal1(root.Right)...)
	return result
}