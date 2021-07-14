package main

import "fmt"

func main() {
	fmt.Println(searchRange([]int{5,7,7,8,8,10},8))
	fmt.Println(searchRange([]int{5,7,7,8,8,10},6))
	fmt.Println(searchRange([]int{},0))
}

func searchRange(nums []int, target int) []int {
	start := halfSearch1(nums,target,0,len(nums) -1)
	end := halfSearch2(nums,target,0,len(nums) -1)
	return []int{start,end}
}
func halfSearch2(nums []int,target int,start int,end int) int{
	left := start
	for start < end  {
		mid := (start + end)/2
		if nums[mid] == target {
			if mid < end {
				if nums[mid+1] > target{
					return mid - left
				} else {
					start = mid + 1
				}
			} else if mid == end {
				return mid - left
			} else {
				return -1
			}
		} else if nums[mid] < target {
			start = mid + 1
		} else {
			end = mid -1
		}
	}
	if start == end {
		if nums[start] == target {
			return start - left
		}
	}
	return -1
}
func halfSearch1(nums []int,target int,start int,end int) int{
	left := start
	for start < end  {
		mid := (start + end)/2
		if nums[mid] == target {
			if mid > start {
				if nums[mid-1] < target{
					return mid - left
				} else {
					end = mid - 1
				}
			} else if mid == start {
				return mid - left
			} else {
				return -1
			}
		} else if nums[mid] < target {
			start = mid + 1
		} else {
			end = mid -1
		}
	}
	if start == end {
		if nums[start] == target {
			return start - left
		}
	}
	return -1
}