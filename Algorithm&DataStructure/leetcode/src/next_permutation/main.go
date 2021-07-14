package main

import (
	"fmt"
	"sort"
)

func main() {
	nums := []int{1,2,5,4,3}
	nextPermutation(nums)
	fmt.Println(nums)
}

func nextPermutation(nums []int)  {
	if len(nums) < 2 {
		return
	}

	i:=len(nums)-1
	for  ;i>0 ;i --  {
		if nums[i] > nums[i-1] {
			break
		}
	}
	if i == 0 {
		sort.Ints(nums)
		return
	}
	nums[i],nums[i-1] = nums[i-1],nums[i]
	if i == len(nums) -1 {
		return
	}
	min := nums[i-1]
	minIndex := -1
	for j := i+1;j < len(nums) ; j++  {
		if nums[j] < min && nums[j]> nums[i] {
			min = nums[j]
			minIndex = j
		}
	}
	if minIndex != -1 {
		nums[i-1],nums[minIndex] = nums[minIndex],nums[i-1]
	}
	sort.Ints(nums[i:])
}
