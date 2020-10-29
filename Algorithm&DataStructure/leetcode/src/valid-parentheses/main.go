package main

import "fmt"

func main() {
	fmt.Println(isValid("[(])"))
	fmt.Println(isValid("()[]{}"))
	fmt.Println(isValid("{[]}"))
	fmt.Println(isValid(""))
}

func isValid(s string) bool {

	stack := Stack{}
	for _, c := range []byte(s) {
		if c == '{' || c == '[' || c == '(' {
			stack.Push(c)
		}
		if c == '}' {
			if stack.IsEmpty() || stack.Pop().(byte) != '{' {
				return false
			}
		}
		if c == ']' {
			if stack.IsEmpty() || stack.Pop().(byte) != '[' {
				return false
			}
		}
		if c == ')' {
			if stack.IsEmpty() || stack.Pop().(byte) != '(' {
				return false
			}
		}
	}
	return stack.IsEmpty()
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
