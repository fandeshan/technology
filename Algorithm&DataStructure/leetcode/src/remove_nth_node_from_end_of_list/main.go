package main

type ListNode struct {
	Val  int
	Next *ListNode
}

func main() {

}

func removeNthFromEnd1(head *ListNode, n int) *ListNode {
	if head == nil {
		return head
	}
	f1 := head
	f2 := head
	i := 0
	for ; i < n; i++ {
		if f1.Next == nil {
			break
		}
		f1 = f1.Next
	}
	if i != n {
		return head.Next
	}
	for f1.Next != nil {
		f1 = f1.Next
		f2 = f2.Next
	}
	f2.Next = f2.Next.Next
	return head
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	if head == nil {
		return head
	}
	nodeStack := Stack{}
	dummy := &ListNode{
		Val:  -1,
		Next: head,
	}
	nodes := dummy
	for nodes != nil {
		nodeStack.Push(nodes)
		nodes = nodes.Next
	}
	lenNodes := len(nodeStack)
	if n > lenNodes-1 || n < 1 {
		return head
	}
	for i := 0; i < n; i++ {
		nodeStack.Pop()
	}
	tmp := nodeStack.Pop().(*ListNode)
	tmp.Next = tmp.Next.Next
	return dummy.Next
}

type Stack []interface{}

func (q *Stack) Push(v interface{}) {
	*q = append(*q, v)
}
func (q *Stack) Pop() interface{} {
	head := (*q)[len(*q)-1]
	*q = (*q)[:len(*q)-1]
	return head
}
func (q *Stack) IsEmpty() bool {
	return len(*q) == 0
}
