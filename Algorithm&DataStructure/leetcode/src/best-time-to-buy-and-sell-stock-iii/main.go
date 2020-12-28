package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(maxProfit([]int{3,3,5,0,0,3,1,4}))
	fmt.Println(maxProfit([]int{1,2,3,4,5}))
}
func maxProfit(prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	//dp := make([][][]int,n)
	//for i :=0 ; i < len(dp) ; i ++ {
	//	dp[i] = make([][]int,2+1)
	//	for j :=0 ; j < len(dp[i]) ; j ++ {
	//		dp[i][j] = make([]int,2)
	//	}
	//}
	a1 := math.MinInt32
	b1 := 0
	c1 := math.MinInt32
	d1 := 0
	for i :=0 ; i < n ; i ++ {
		if i == 0 {
			//dp[i][0][1] = math.MinInt32
			//dp[i][0][0] = 0
			//dp[i][1][1] = dp[i][0][0]-prices[i]
			//dp[i][1][0] = 0
			//dp[i][2][1] = dp[i][1][0]-prices[i]
			//dp[i][2][0] = 0
			continue
		}

		//dp[i][1][1] = max(dp[i-1][1][1],dp[i-1][0][0] - prices[i])
		//dp[i][1][0] = max(dp[i-1][1][0],dp[i-1][1][1] + prices[i])
		//dp[i][2][1] = max(dp[i-1][2][1],dp[i-1][1][0] - prices[i])
		//dp[i][2][0] = max(dp[i-1][2][0],dp[i-1][2][1] + prices[i])
		a1 = max(a1, 0  - prices[i])
		b1 = max(b1, a1 + prices[i])
		c1 = max(c1, b1 - prices[i])
		d1 = max(d1, c1 + prices[i])

	}
	//fmt.Println(dp)
	//return dp[n-1][2][0]
	return d1
}

func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}

func maxProfit1(prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	dp := make([][][]int,n)
	for i :=0 ; i < len(dp) ; i ++ {
		dp[i] = make([][]int,2+1)//最多买两次
		for j :=0 ; j < len(dp[i]) ; j ++ {
			//0代表未持有，1代表已持有
			dp[i][j] = make([]int,2)
			if i == 0  {
				dp[i][j][0] = 0
				if j == 0 {
					//表示不可能
					dp[i][j][1] = math.MinInt32
				} else {
					//表面第0天买入
					dp[i][j][1] = 0 - prices[i]
				}
				continue
			}
			if j == 0 {
				dp[i][j][0] = 0
				//表示不可能
				dp[i][j][1] = math.MinInt32
				continue
			}
			dp[i][j][0] = max(dp[i-1][j][0],dp[i-1][j][1] + prices[i])
			dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i])
		}
	}
	return dp[n-1][2][0]
}

