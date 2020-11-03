package main

import "fmt"

func main() {
	fmt.Println(findMin([]int{2,2,2,0,1}))
	fmt.Println(findMin([]int{1,1,1,0,1}))
	fmt.Println(findMin([]int{1,3,5}))
	fmt.Println(findMin([]int{1,1,1,1,1}))
	fmt.Println(findMin([]int{1,}))
	fmt.Println(findMin([]int{1,1,1,1,1,1}))
	fmt.Println(findMin([]int{1,1,1,1,1,2}))
	fmt.Println(findMin([]int{1,1,1,1,1,0}))
	fmt.Println(findMin([]int{1,3,3}))
	fmt.Println(findMin([]int{10,1,10,10,10}))
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

		} else if nums[mid] == nums[start] {
			if nums[end] > nums[mid] {
				return nums[start]
			}else if nums[end] == nums[mid] {
				start++
			} else{
					start = mid
			}
		} else {
			end = mid
		}
	}
	if nums[start] > nums[end] {
		return nums[end]
	}
	return nums[start]
}