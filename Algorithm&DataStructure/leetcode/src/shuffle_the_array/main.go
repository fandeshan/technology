package main

import "fmt"

func main() {
	fmt.Println(shuffle([]int{2,5,1,3,4,7},3))
	fmt.Println(shuffle([]int{1,2,3,4,4,3,2,1},4))
	fmt.Println(shuffle([]int{1,1,2,2},2))
}

func shuffle(nums []int, n int) []int {
	if len(nums) < 3 || len(nums) != 2*n {
		return nums
	}
	result := make([]int,2*n)
	for i := 0;i < n ;i++  {
		result[2*i] = nums[i]
		result[2*i+1] = nums[i+n]
	}
	return result
}

func shuffle1(nums []int, n int) []int {
	if len(nums) < 3 || len(nums) != 2*n {
		return nums
	}

	for i := 0;i < n ;i++  {
		tmp := nums[n+i]
		for k:=n+i;k>2*i;k--  {
			nums[k] = nums[k-1]
		}
		nums[2*i+1] = tmp
	}
	return nums
}