package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(removeDuplicates1([]int{1,1,2}))
	fmt.Println(removeDuplicates1([]int{1,1,1,2,2}))
	fmt.Println(removeDuplicates1([]int{0,0,1,1,1,2,2,3,3,4}))
}

func removeDuplicates(nums []int) int {
	if len(nums) < 2 {
		return len(nums)
	}
	nums1 := nums
	result := 1
	for i := 1; i < len(nums) ; i++  {
		if nums[i] == nums[i -1] {
			nums[i -1],nums[0] = nums[0],nums[i-1]
			nums = nums[1:]
			i --
		} else {
			result ++
		}
	}
	sub := len(nums1) - len(nums)
	for i := 0;i < len(nums) ;i++  {
		nums1[i] = nums1[sub +i]
	}
	sort.Ints(nums1[:len(nums)])
	fmt.Println(nums)
	fmt.Println(nums1)
	return result
}


func removeDuplicates1(nums []int) int {
	slow,fast := 0, 1
	for ;fast<len(nums);fast++{
		if nums[slow] != nums[fast] {
			slow++
			nums[slow] = nums[fast]
		}
	}
	return slow+1
}