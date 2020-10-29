package main

type Node struct {
	Val   int
	Left  *Node
	Right *Node
	Next  *Node
}

func main() {

}

/**
 * Definition for a Node.
 *
 */

func connect(root *Node) *Node {
	if root == nil || root.Left == nil {
		return root
	}
	root.Left.Next = root.Right
	//if root.Left.Right != nil {
	//	root.Left.Right.Next = root.Right.Left
	//}
	rl := root.Left
	rr := root.Right
	for rl.Right != nil {
		rl.Right.Next = rr.Left
		rl = rl.Right
		rr = rr.Left
	}
	root.Left = connect(root.Left)
	root.Right = connect(root.Right)
	return root
}
