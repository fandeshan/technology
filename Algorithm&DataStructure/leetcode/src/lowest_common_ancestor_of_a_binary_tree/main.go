package main
type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}
func main() {
	
}

/**
 * Definition for TreeNode.
 * type TreeNode struct {
 *     Val int
 *     Left *ListNode
 *     Right *ListNode
 * }
 */
func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	if root == nil || root == p || root == q {
		return root
	}
	left := lowestCommonAncestor(root.Left,p,q)
	right := lowestCommonAncestor(root.Right,p,q)
	if left != nil && right != nil {
		return root
	} else if left != nil {
		return left
	} else if right != nil {
		return right
	}
	return nil
}