package main

import "fmt"

func main() {
	fmt.Println(rob([]int{1,2,3,1}))
	fmt.Println(rob([]int{2,7,9,3,1}))
	fmt.Println(rob([]int{2,1,1,2}))
}

func rob(nums []int) int {
	n := len(nums)
	if n < 1 {
		return 0
	}

	if n == 1 {
		return nums[0]
	}
	maxFirst := max(nums[0],nums[1])
	if n == 2 {
		return maxFirst
	}
	maxAmouts := make([]int,3)
	maxAmouts[0] = nums[0]
	maxAmouts[1] = maxFirst
	for i := 2; i < n ; i++  {
		maxAmouts[i%3] = max(maxAmouts[(i-2)%3]+nums[i],maxAmouts[(i-1)%3])
	}
	return maxAmouts[(n-1)%3]
}
func max(a int,b int) int {
	if a > b {
		return a
	}
	return b
}