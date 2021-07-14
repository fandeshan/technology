package main

import "fmt"

func main() {
	fmt.Println(getSum(1,2))
	fmt.Println(getSum(-2,3))
}

func getSum(a int, b int) int {
	if b == 0 {
		return a
	}
	return getSum(a^b,(a&b)<<1)
}