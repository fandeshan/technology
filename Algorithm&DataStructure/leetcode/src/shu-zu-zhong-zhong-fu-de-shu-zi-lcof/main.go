package main

import "fmt"

func main() {
	fmt.Println(findRepeatNumber([]int{2, 3, 1, 0, 2, 5, 3}))
}

func findRepeatNumber(nums []int) int {
	if len(nums) < 2 {
		return -1
	}
	numMap := make(map[int]int)
	for i := 0;i < len(nums) ;i ++  {
		if _,ok := numMap[nums[i]];ok {
			return nums[i]
		} else {
			numMap[nums[i]] = 1
		}
	}
	return -1
}