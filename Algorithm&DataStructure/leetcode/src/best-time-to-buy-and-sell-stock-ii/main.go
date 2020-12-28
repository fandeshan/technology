package main

import "math"

func main() {

}

func maxProfit(prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	a := 0
	b := math.MinInt32
	for i :=0 ; i < n ;i++ {
		tmp := a
		a = max(tmp, b+prices[i])
		b = max(b,tmp-prices[i])
	}
	return a
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}

func maxProfit1(prices []int) int {
	if len(prices) < 2 {
		return 0
	}
	result := 0
	for i:=1;i<len(prices);i++{
		tmp:=prices[i]-prices[i-1]
		if tmp > 0{
			result += tmp
		}
	}
	return result
}