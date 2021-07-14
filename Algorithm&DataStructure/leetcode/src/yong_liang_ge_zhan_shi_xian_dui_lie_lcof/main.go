package main

func main() {

}

type CQueue struct {
	stackLeft Stack
	stackRight Stack
}


func Constructor() CQueue {
	return CQueue{
		stackLeft:  Stack{},
		stackRight: Stack{},
	}
}


func (this *CQueue) AppendTail(value int)  {
	this.stackLeft.Push(value)
}


func (this *CQueue) DeleteHead() int {
	if this.stackLeft.IsEmpty()&&this.stackRight.IsEmpty() {
		return -1
	}
	if this.stackRight.IsEmpty() {
		for !this.stackLeft.IsEmpty()  {
			this.stackRight.Push(this.stackLeft.Pop())
		}
	}
	return this.stackRight.Pop().(int)
}

type Stack [] interface{}

func (q *Stack) Push(v interface{})  {
	*q = append(*q,v)
}
func (q *Stack) Pop() interface{}  {
	head := (*q)[len(*q)-1]
	*q = (*q)[:len(*q)-1]
	return head
}
func (q *Stack) Peek() interface{}  {
	head := (*q)[len(*q)-1]
	return head
}
func (q *Stack) IsEmpty() bool {
	return len(*q) == 0
}
/**
 * Your CQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AppendTail(value);
 * param_2 := obj.DeleteHead();
 */