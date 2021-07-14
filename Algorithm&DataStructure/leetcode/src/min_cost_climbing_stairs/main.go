package main

import "fmt"

func main() {
	fmt.Println(minCostClimbingStairs([]int{10, 15, 20}))
	fmt.Println(minCostClimbingStairs([]int{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}))
}

func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	if n < 2 {
		return 0
	}
	dp := make([]int,n+1)
	dp[0] = 0
	dp[1] = 0
	for i := 2 ; i <= n ; i ++ {
		dp[i] = min(dp[i-1] + cost[i-1],dp[i-2]+cost[i-2])
	}
	fmt.Println(dp)
	return dp[n]
}
func min(a int,b int) int {
	if a < b {
		return a
	}
	return b
}