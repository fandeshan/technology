package main

import "fmt"

func main() {
	fmt.Println(maxProfit([]int{1, 3, 2, 8, 4, 9},2))
}

func maxProfit(prices []int, fee int) int {
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
