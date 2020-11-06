package main

import "fmt"

func main() {
	fmt.Println(twoSum([]int{2,7,11,15},9))
}

func twoSum(nums []int, target int) []int {
	if len(nums) < 2 {
		return nil
	}
	left := 0
	right := len(nums)-1
	for left < right  {
		if nums[left] + nums[right] > target {
			right --
		}else if nums[left] + nums[right] < target {
			left ++
		}else {
			return []int{nums[left],nums[right]}
		}
	}
	return nil
}
