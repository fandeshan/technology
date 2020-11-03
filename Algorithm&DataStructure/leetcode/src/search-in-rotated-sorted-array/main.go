package main

import "fmt"

func main() {
	fmt.Println(search([]int{1,2,3,4,5,6},6))
	fmt.Println(search([]int{4,5,6,7,0,1,2},0))
	fmt.Println(search([]int{4,5,6,7,0,1,2},3))
	fmt.Println(search([]int{1},0))
}

func search(nums []int, target int) int {
	start := 0
	end := len(nums)-1
	for start+1 < end  {
		mid := (end + start)/2
		if nums[mid] == target {
			return mid
		}
		if nums[mid] > nums[start] {
			if target >= nums[start] && nums[mid] > target {
				end = mid
			}else {
				start = mid
			}
		}else {
			if target > nums[mid] && nums[end] >= target {
				start = mid
			}else {
				end = mid
			}
		}
	}
	if nums[start] == target {
		return start
	}
	if nums[end] == target {
		return end
	}
	return -1
}
//原始二分法
func searchOrigin(nums []int, target int) int {
	start := 0
	end := len(nums)-1
	for start+1 < end  {
		mid := (end + start)/2
		if nums[mid] == target {
			return mid
		}else if nums[mid] < target {
			start = mid
		}else {
			end = mid
		}
	}
	if nums[start] == target {
		return start
	}
	if nums[end] == target {
		return end
	}
	return -1
}