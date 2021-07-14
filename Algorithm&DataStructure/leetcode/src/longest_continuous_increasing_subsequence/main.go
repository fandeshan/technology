package main

import "fmt"

func main() {
	fmt.Println(findLengthOfLCIS([]int{1,3,5,4,7}))
	fmt.Println(findLengthOfLCIS([]int{2,2,2,2,2}))
}

func findLengthOfLCIS(nums []int) int {
	n := len(nums)
	if len(nums) < 1 {
		return 0
	}
	max :=1
	right :=1
	tmp := 1
	for right < n {
		if nums[right] > nums[right-1] {
			tmp ++
			if tmp > max {
				max = tmp
			}
		} else {
			tmp = 1
		}
		right ++
	}
	return max
}