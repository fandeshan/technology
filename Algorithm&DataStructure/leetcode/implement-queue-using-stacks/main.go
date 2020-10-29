package main

func main() {

}

type MyQueue struct {
	stackLeft  Stack
	stackRight Stack
}

/** Initialize your data structure here. */
func Constructor() MyQueue {
	return MyQueue{
		stackLeft:  Stack{},
		stackRight: Stack{},
	}
}

/** Push element x to the back of queue. */
func (this *MyQueue) Push(x int) {
	this.stackLeft.Push(x)
}

/** Removes the element from in front of queue and returns that element. */
func (this *MyQueue) Pop() int {
	if this.Empty() {
		return -1
	}
	if this.stackRight.IsEmpty() {
		for !this.stackLeft.IsEmpty() {
			this.stackRight.Push(this.stackLeft.Pop())
		}
	}
	return this.stackRight.Pop().(int)
}

/** Get the front element. */
func (this *MyQueue) Peek() int {
	if this.Empty() {
		return -1
	}
	if this.stackRight.IsEmpty() {
		for !this.stackLeft.IsEmpty() {
			this.stackRight.Push(this.stackLeft.Pop())
		}
	}
	return this.stackRight.Peek().(int)
}

/** Returns whether the queue is empty. */
func (this *MyQueue) Empty() bool {
	return this.stackLeft.IsEmpty() && this.stackRight.IsEmpty()
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
func (q *Stack) Peek() interface{} {
	head := (*q)[len(*q)-1]
	return head
}
func (q *Stack) IsEmpty() bool {
	return len(*q) == 0
}
