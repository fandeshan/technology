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

func postorder(root *Node) []int {
	if root == nil {
		return nil
	}
	if len(root.Children) < 1 {
		return []int{root.Val}
	}
	result := []int{}
	for _,v := range root.Children {
		result = append(result,postorder(v)...)
	}
	result = append(result,root.Val)
	return result
}