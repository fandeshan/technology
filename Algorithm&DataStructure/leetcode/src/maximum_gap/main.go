package main

import "fmt"

func main() {
	fmt.Println(maximumGap([]int{3,6,9,1}))
}

func maximumGap(nums []int) int {
	if len(nums) < 2 {
		return 0
	}
	quickSort(nums,0,len(nums)-1)
	//fmt.Println(nums)
	result := 0
	for i := 1; i < len(nums) ; i++  {
		tmp := nums[i] - nums[i-1]
		if tmp > result {
			result = tmp
		}
	}
	return result
}
func quickSort(nums []int,start int,end int)  {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start]
	for left <= right  {
		for left <= right && nums[left] < base  {
			left ++
		}
		for left <= right && nums[right] > base  {
			right --
		}
		if left <= right {
			nums[left],nums[right] = nums[right],nums[left]
			left ++
			right --
		}
	}
	quickSort(nums,start,right)
	quickSort(nums,left,end)
}