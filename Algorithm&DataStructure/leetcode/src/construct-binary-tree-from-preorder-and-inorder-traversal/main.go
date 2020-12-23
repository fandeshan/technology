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
func buildTree(preorder []int, inorder []int) *TreeNode {
	if len(preorder) < 1 {
		return nil
	}
	i:=0
	for ;i < len(inorder);i++{
		if inorder[i] == preorder[0] {
			break
		}
	}
	node := &TreeNode{
		Val: preorder[0],

	}
	node.Left = buildTree(preorder[1:i+1],inorder[:i])
	node.Right = buildTree(preorder[i+1:],inorder[i+1:])
	return node
}