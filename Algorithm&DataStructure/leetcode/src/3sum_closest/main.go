package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(threeSumClosest([]int{-1,2,1,-4},1))
	fmt.Println(threeSumClosest([]int{1,2,4,8,16,32,64,128},82))
}
func threeSumClosest(nums []int, target int) int {
	n := len(nums)
	sort.Ints(nums)
	result := nums[2]+nums[1]+nums[0]
	for i:=0;i < n ;i++  {
		left :=i + 1
		right := n -1
		for left < right {
			sum := nums[i] + nums[left] + nums[right]
			if sum - target > 0 {
				if sum - target < abs(result-target) {
					result = sum
				}
				right --
			}else {
				if target - sum  < abs(result-target) {
					result = sum
				}
				left ++
			}
		}
	}
	return result
}
func threeSumClosest1(nums []int, target int) int {
	n := len(nums)
	sort.Ints(nums)
	fmt.Println(nums)
	result := nums[2]+nums[1]+nums[0]
	for i:=0;i < n ;i++  {
		for j:=i+1;j < n ;j++  {
			for k := j+1;k < n ;k++  {
				sum := nums[i]+nums[j]+nums[k]
				fmt.Println(sum)
				if abs(result - target) > abs(sum - target) {
					result = sum
				}
				if sum - target > 0 {
					return result
				}
			}

		}

	}
	return result
}
func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}