package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(maxProfit([]int{1, 3, 2, 8, 4, 9},2))
}

func maxProfit(prices []int, fee int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	a := 0
	b := math.MinInt32
	for i :=0 ; i < n ;i++ {
		tmp := a
		a = max(tmp, b+prices[i])
		b = max(b,tmp-prices[i]-fee)
	}
	return a
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}

func maxProfit1(prices []int, fee int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	min := prices[0]
	result := 0
	for i := 1 ; i < n ; i ++ {
		if prices[i] < min {
			min = prices[i]
		} else if prices[i] - min - fee > 0 {
			result += prices[i] - min - fee
			min = prices[i] - fee
		}
	}
	return result

}
