package main

import "fmt"

func main() {
	fmt.Println(findMin([]int{3,4,5,1,2}))
	fmt.Println(findMin([]int{4,5,6,7,0,1,2}))
	fmt.Println(findMin([]int{1}))
	fmt.Println(findMin([]int{1,2}))
}

func findMin(nums []int) int {
	start := 0
	end := len(nums)-1

	for start+1 < end  {
		mid := (end + start)/2
		if nums[mid] > nums[start] {
			if nums[end] < nums[mid]{
				start = mid
			}else {
				return nums[start]
			}

		}else {
			end = mid
		}
	}
	if nums[start] > nums[end] {
		return nums[end]
	}
	return nums[start]
}