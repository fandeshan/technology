package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(maxProfit(2,[]int{2,4,1}))
	fmt.Println(maxProfit(2,[]int{3,2,6,5,0,3}))
	fmt.Println(maxProfit(1,[]int{1,2}))
	fmt.Println(maxProfit(2,[]int{3,3,5,0,0,3,1,4}))
}

func maxProfit(k int, prices []int) int {
	if len(prices) < 1 || k <  1 {
		return 0
	}
	n := len(prices)
	k = min(k,n/2)
	dp := make([][][]int,n+1)
	for i := 0 ; i < n+1; i ++ {
		dp[i] = make([][]int,k+1)
		for j := 0 ;j < k+1;j++ {
			dp[i][j] = make([]int,2)
			dp[i][j][0] = 0
			dp[i][j][1] = math.MinInt32
		}
	}
	for i := 1 ; i < n+1; i ++ {
		for j := 1 ;j < k+1;j++ {
			dp[i][j][0] = max(dp[i-1][j][0],dp[i-1][j][1]+prices[i-1])
			dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i-1])
		}
	}
	//for i := 0 ; i < n+1; i ++ {
	//	for j := 0 ;j < k+1;j++ {
	//		fmt.Print(dp[i][j][0],dp[i][j][1])
	//		fmt.Println()
	//	}
	//	fmt.Println()
	//}
	return dp[n][k][0]
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}
func min(a,b int) int {
	if a < b {
		return a
	}
	return b
}