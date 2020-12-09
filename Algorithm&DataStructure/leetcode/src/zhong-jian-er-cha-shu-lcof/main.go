package main
type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}
func main() {
	
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
	root := &TreeNode{
		Val:   preorder[0],
		Left:  nil,
		Right: nil,
	}
	parent := root
	tail := root
	for i := 0; i<len(inorder) ;i++  {
		for j:=0;j<len(preorder) ;j++  {
			if inorder[i] == preorder[j] {

			}
		}
	}
}