package main

import (
	"fmt"
)

func main() {
	fmt.Println(lengthOfLIS([]int{10,9,2,5,3,7,101,18}))
	fmt.Println(lengthOfLIS([]int{0,1,0,3,2,3}))
	fmt.Println(lengthOfLIS([]int{7,7,7,7,7,7,7}))
}

func lengthOfLIS(nums []int) int {
	dp := make([]int,len(nums))
	dp[0] = 1
	maxLen := 1;
	for i := 1; i < len(nums); i++ {
		dp[i] = 1
		for j:=0;j<i;j++ {
			if nums[j] < nums[i] {
				dp[i] = max(dp[j]+1,dp[i])
			}
		}
		maxLen = max(dp[i],maxLen)
	}
	return maxLen

}
func max(a int,b int) int{
	if a > b {
		return a
	}
	return b
}