package main

import "fmt"

func main() {
	fmt.Println(trailingZeroes(24))
}

func trailingZeroes(n int) int {
	cnt := 0
	for n > 0  {
		cnt += n/5
		n = n/5
	}
	return cnt
}