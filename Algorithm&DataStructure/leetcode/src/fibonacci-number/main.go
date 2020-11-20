package main

import (
	"fmt"
)

func main() {
	fmt.Println(fib(30))
}

func fib(N int) int {
	if N < 1 {
		return 0
	}
	if N < 3 {
		return 1
	}
	result := [3]int{1,1}

	for i := 2;i < N ;i ++  {
		result[i%3] = result[(i-1)%3] + result[(i-2)%3]
	}
	return result[(N-1)%3]
}
