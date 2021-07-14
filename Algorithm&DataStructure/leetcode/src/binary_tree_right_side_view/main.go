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

func rightSideView(root *TreeNode) []int {
	if root == nil {
		 return  nil
	}
	result := []int{root.Val}
	queue := []*TreeNode{root}
	for len(queue) > 0  {
		right := true
		queueSize := len(queue)
		for i := 0; i< queueSize ;i++  {
			node := queue[0]
			queue = queue[1:]
			if right {
				result = append(result,node.Val)
				right = !right
			}
			if node.Right != nil {
				queue = append(queue,node.Right)
			}
			if node.Left != nil {
				queue = append(queue,node.Left)
			}

		}
	}
	return result
}

func rightSideView1(root *TreeNode) []int {
	if root == nil {
		return  nil
	}
	result := []int{}
	var travelTree func (node *TreeNode, level int)
	travelTree =  func (node *TreeNode, level int) {
		if node == nil {
			return
		}
		if len(result) < level {
			result = append(result,node.Val)
		}
		travelTree(node.Right,level + 1)
		travelTree(node.Left,level + 1)
	}
	travelTree(root,1)
	return result
}
