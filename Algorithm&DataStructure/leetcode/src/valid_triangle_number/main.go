package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(triangleNumber([]int{2,2,3,4}))
	fmt.Println(triangleNumber([]int{1,2,3,4,5,6}))
}

func triangleNumber(nums []int) int {
	if len(nums) < 3 {
		return 0
	}
	sort.Ints(nums)
	result := 0
	for i:=len(nums)-1;i>=2 ;i--  {
		left := 0
		right := i-1
		for left < right {
			if nums[left]+nums[right] > nums[i] {
				result = result + right - left
				right --
			}else {
				left ++
			}
		}
	}
	return result
}