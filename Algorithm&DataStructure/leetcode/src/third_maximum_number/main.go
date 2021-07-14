package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(thirdMax([]int{3, 2, 1}))
	fmt.Println(thirdMax([]int{1,2}))
	fmt.Println(thirdMax([]int{2, 2, 3, 1}))
	fmt.Println(thirdMax([]int{2, 2, 3, 3}))
	fmt.Println(thirdMax([]int{1,2,-2147483648}))
}

func thirdMax(nums []int) int {
	if len(nums) < 1 {
		return 0
	}
	if len(nums) < 3 {

	}
	maxArr := make([]int,3)
	for i:=1;i<len(maxArr) ;i++  {
		maxArr[i] = math.MinInt64
	}
	for i := 0;i < len(nums) ;i++  {
		if nums[i] > maxArr[0] {
			maxArr[0] = nums[i]
		}
	}
	findIntervalMax(nums,maxArr,1,maxArr[0])
	if math.MinInt64 == maxArr[len(maxArr) - 1] {
		return maxArr[0]
	}
	return maxArr[len(maxArr) - 1]
}
func findIntervalMax(nums []int,maxArr []int,pos int,lastMax int) {
	if pos + 1 > len(maxArr) {
		return
	}
	for i:=0;i<len(nums) ;i++  {
		if nums[i] > maxArr[pos] && nums[i] < lastMax {
			maxArr[pos] = nums[i]
		}
	}
	if math.MinInt64 == maxArr[pos] {
		return
	}
	findIntervalMax(nums,maxArr,pos+1, maxArr[pos])
}
