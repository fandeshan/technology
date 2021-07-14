package main

import "fmt"

func main() {
	fmt.Println(numWays(10000))
}
func numWays(n int) int {
	if n < 1 {
		return 1
	}
	if n < 4 {
		return n
	}
	result := [3]int{1,2,3}
	for i := 3;i < n ;i ++  {
		result[i%3] =  (result[(i-1)%3] + result[(i-2)%3])%(1e9+7)
	}
	return result[(n-1)%3]
}