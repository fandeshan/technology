package main

import "fmt"

func main() {
	fmt.Println(wiggleMaxLength([]int{1,17,5,10,13,15,10,5,16,8}))
}

func wiggleMaxLength(nums []int) int {
	n := len(nums)
	if n < 2{
		return n
	}
	high := 1
	low := 1
	for i := 1 ; i < n ; i++ {
		if nums[i] > nums[i-1] {
			high = low + 1
		}
		if nums[i] < nums[i-1] {
			low = high +1
		}
	}
	return max(high,low)
}
func max(a int ,b int) int {
	if a > b {
		return a
	}
	return b
}