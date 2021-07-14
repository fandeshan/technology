package main

import "fmt"

func main() {
	fmt.Println(getSumAbsoluteDifferences([]int{2,3,5}))
	fmt.Println(getSumAbsoluteDifferences([]int{1,4,6,8,10}))
}

func getSumAbsoluteDifferences(nums []int) []int {
	n := len(nums)
	arr := make([]int,n)
	sums := make([]int,n)
	sums[0] = nums[0]
	for i :=1 ; i < n ; i ++ {
		sums[i] += sums[i-1]+ nums[i]
	}
	//arr[0] = sums[n-1] - n*nums[0]
	for i :=0 ; i < n ; i ++ {
		arr[i] = (i+1)*nums[i]- sums[i] + (sums[n-1] -sums[i]) - (n-i-1) * nums[i]
	}
	return arr
}
func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}