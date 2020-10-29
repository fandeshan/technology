package main

func main() {

}

type MyStack struct {
	queue1 Queue
	queue2 Queue
}

/** Initialize your data structure here. */
func Constructor() MyStack {
	return MyStack{
		queue1: Queue{},
		queue2: Queue{},
	}
}

/** Push element x onto stack. */
func (this *MyStack) Push(x int) {
	this.queue1.Push(x)
	for !this.queue2.IsEmpty() {
		this.queue1.Push(this.queue2.Pop())
	}
	temp := this.queue2
	this.queue2 = this.queue1
	this.queue1 = temp
}

/** Removes the element on top of the stack and returns that element. */
func (this *MyStack) Pop() int {
	return this.queue2.Pop().(int)
}

/** Get the top element. */
func (this *MyStack) Top() int {
	return this.queue2.Top().(int)
}

/** Returns whether the stack is empty. */
func (this *MyStack) Empty() bool {
	return this.queue2.IsEmpty()
}

type Queue []interface{}

func (q *Queue) Push(v interface{}) {
	*q = append(*q, v)
}
func (q *Queue) Pop() interface{} {
	head := (*q)[0]
	*q = (*q)[1:]
	return head
}
func (q *Queue) Top() interface{} {
	head := (*q)[0]
	return head
}
func (q *Queue) IsEmpty() bool {
	return len(*q) == 0
}
