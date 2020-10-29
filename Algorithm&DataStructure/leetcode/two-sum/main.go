package main

import "fmt"

func main() {
	nums := []int{2, 7, 11, 15}
	target := 9
	result := twoSum(nums, target)
	fmt.Printf("[%d, %d]\n", result[0], result[1])
}

func twoSum(nums []int, target int) []int {
	result := []int{0, 0}
	lenNums := len(nums)
	findSum := false
	for i := 0; i < lenNums; i++ {
		for j := i + 1; j < lenNums; j++ {
			if nums[i]+nums[j] == target {
				result[0] = i
				result[1] = j
				findSum = true
				break
			}

		}
		if findSum {
			break
		}
	}
	return result
}
