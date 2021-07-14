package main

import "fmt"

func main() {
	a := 2<<30 - 1
	fmt.Println(-2 << 30)
	fmt.Println(2<<30 - 1)
	fmt.Println(a)
	//fmt.Println(reverse(123))
	//fmt.Println(reverse(100))
	//fmt.Println(reverse(120))
	//fmt.Println(reverse(-321))
}

func reverse(x int) int {
	numStack := Stack{}
	i := x
	for ; i >= 10 || i <= -10; i = i / 10 {
		numStack.Push(i % 10)
	}
	numStack.Push(i)
	result := 0
	muti := 1
	for !numStack.IsEmpty() {
		result = result + muti*numStack.Pop().(int)
		muti = muti * 10
	}
	if result > (2<<30-1) || result < (-2<<30) {
		return 0
	}
	return result
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
