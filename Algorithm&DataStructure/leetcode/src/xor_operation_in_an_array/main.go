package main

import "fmt"

func main() {
	fmt.Println(xorOperation(5,0))
	fmt.Println(xorOperation(4,3))
	fmt.Println(xorOperation(1,7))
}

func xorOperation(n int, start int) int {
	result := start
	i := 1
	for i < n {
		result = result^(start + 2*i)
		i++
	}
	return result
}