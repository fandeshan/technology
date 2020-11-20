package main

import "fmt"

func main() {
	fmt.Println(climbStairs(3))
	fmt.Println(climbStairs(4))
	fmt.Println(climbStairs(5))
}

func climbStairs(n int) int {
	if n < 4 {
		return n
	}
	result := [3]int{1,2,3}
	for i := 3;i < n ;i ++  {
		result[i%3] =  result[(i-1)%3] + result[(i-2)%3]
	}
	return result[(n-1)%3]
}
