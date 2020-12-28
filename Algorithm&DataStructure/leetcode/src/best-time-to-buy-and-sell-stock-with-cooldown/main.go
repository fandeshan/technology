package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(maxProfit([]int{1,2,3,0,2}))
}

func maxProfit(prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	a := 0
	b := math.MinInt32
	aPre := 0
	for i :=0 ; i < n ;i++ {
		tmp := a
		a = max(tmp, b+prices[i])
		b = max(b,aPre-prices[i])
		aPre = tmp
	}
	return a
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}