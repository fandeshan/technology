package main

import "fmt"

/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */
type Node struct {
	Val    int
	Next   *Node
	Random *Node
}

func (n *Node) printRandom() {
	if n == nil {
		return
	}
	fmt.Print(n.Val)
	fmt.Print("--->")
	if n.Random != nil {
		fmt.Print(n.Random.Val)
		fmt.Print("--->")
	}
	fmt.Println()
	n.Next.printRandom()
}
func main() {

	head0 := &Node{
		Val:    7,
		Next:   nil,
		Random: nil,
	}
	head1 := &Node{
		Val:    13,
		Next:   nil,
		Random: nil,
	}
	head2 := &Node{
		Val:    11,
		Next:   nil,
		Random: nil,
	}
	head3 := &Node{
		Val:    10,
		Next:   nil,
		Random: nil,
	}
	head4 := &Node{
		Val:    1,
		Next:   nil,
		Random: nil,
	}
	head0.Next = head1
	head1.Next = head2
	head1.Random = head0
	head2.Next = head3
	head2.Random = head4
	head3.Next = head4
	head3.Random = head2
	head4.Random = head0
	head0.printRandom()
	result := copyRandomList(head0)
	fmt.Println("=========================")
	result.printRandom()
}
func copyRandomList(head *Node) *Node {
	if head == nil {
		return nil
	}
	copy(head)
	copyRandom(head)
	return split(head)
}
func copy(head *Node) {
	node := head
	for node != nil {
		next := node.Next
		newNode := &Node{
			Val:    node.Val,
			Next:   node.Next,
			Random: nil,
		}
		node.Next = newNode
		node = next
	}
}
func copyRandom(head *Node) {
	node := head
	for node != nil && node.Next != nil {
		if node.Random != nil {
			node.Next.Random = node.Random.Next
		}
		node = node.Next.Next
	}
}
func split(head *Node) *Node {
	result := head.Next
	move := head.Next
	for head != nil {
		head.Next = head.Next.Next
		if move != nil && move.Next != nil {
			move.Next = move.Next.Next
			move = move.Next
		}
		head = head.Next
	}
	return result
}
