package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(maxProfit([]int{7,1,5,3,6,4}))
	fmt.Println(maxProfit([]int{7,6,4,3,1}))
}

func maxProfit(prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	a := 0
	b := math.MinInt32
	for i :=0 ; i < n ;i++ {
		a = max(a,b+prices[i])
		b = max(b,-prices[i])
	}
	return a
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}