package main

import "fmt"

func main() {
	fmt.Println(findPeakElement([]int{1,2,1,3,4,3,1}))
	fmt.Println(findPeakElement([]int{1,2,1,3,5,6,4}))
	fmt.Println(findPeakElement([]int{1}))
}

func findPeakElement(nums []int) int {
	start := 0
	end := len(nums)-1
	for start+1 < end  {
		mid := (end + start)/2
		if nums[mid]>nums[mid-1] {
			if nums[mid+1]< nums[mid] {
				return mid
			}
			start = mid
		}else{
			end = mid
		}
	}
	if nums[start]>nums[end] {
		return start
	}else{
		return end
	}
}