package main

import "fmt"

func main() {
	fmt.Println(subarraySum([]int{1,1,1},2))
}

func subarraySum(nums []int, k int) int {
	if len(nums) < 1 {
		return 0
	}
	sumMap := make(map[int]int)
	sumMap[0] = 1
	result := 0
	sum :=0
	for i:=0;i<len(nums) ;i++  {
		sum += nums[i]
		if _,ok := sumMap[sum -k];ok {
			result += sumMap[sum -k]
		}
		sumMap[sum]++
	}

	return result
}