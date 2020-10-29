package main

import "fmt"

func main() {
	fmt.Println(runningSum([]int{1, 2, 3, 4}))
}

func runningSum(nums []int) []int {
	sumTmp := 0
	for i := 0; i < len(nums); i++ {
		sumTmp = sumTmp + nums[i]
		nums[i] = sumTmp
	}
	return nums
}
