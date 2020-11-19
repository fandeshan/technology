package main

type Node struct {
	Val int
	Children []*Node
}

func main() {
	
}

/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func preorder(root *Node) []int {
	if root == nil {
		return nil
	}
	result := []int{root.Val}
	for _,v := range root.Children {
		result = append(result,preorder(v)...)
	}
	return result
}