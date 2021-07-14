package main

import (
	"fmt"
	"sort"
)

func main() {

	fmt.Println(purchasePlans([]int{2,2,1,9},10))
	fmt.Println(purchasePlans([]int{1,2,3,4,5,6,7,8,9},17))
}

func purchasePlans(nums []int, target int) int {
	sort.Ints(nums)
	sum := 0
	for i :=0 ; i < len(nums);i++ {
		if nums[i] >= target {
			break
		}
		j := halfSearch(nums,target-nums[i],i+1,len(nums)-1)
		if j <=0 {
			break
		}
		sum = (sum+j)%(1e9+7)
	}
	return sum
}

func halfSearch(nums []int,target int,start int,end int) int{
	left := start

	for start < end  {
		mid := (start + end)/2
		if nums[mid] <= target {
			if mid < end {
				if nums[mid+1] > target{
					return mid - left + 1
				} else {
					start = mid + 1
				}
			} else if mid == end {
				return mid - left + 1
			} else {
				return 0
			}
		}else {
			end = mid
		}
	}
	if start == end {
		if nums[start] <= target {
			return start - left + 1
		}

	}
	return 0
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