package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Println(summaryRanges([]int{0,1,2,4,5,7}))
	fmt.Println(summaryRanges([]int{0,2,3,4,6,8,9}))
	fmt.Println(summaryRanges([]int{}))
	fmt.Println(summaryRanges([]int{-1}))
	fmt.Println(summaryRanges([]int{0}))
}

func summaryRanges(nums []int) []string {
	if len(nums) < 1 {
		return nil
	}
	start,end := nums[0],nums[0]
	result := []string{}
	for i := 1 ; i < len(nums) ; i++ {
		if nums[i] == end + 1 {
			end ++
		} else {
			if end == start {
				result = append(result,strconv.Itoa(start))
			} else {
				result = append(result,strconv.Itoa(start)+"->"+strconv.Itoa(end))
			}
			start,end = nums[i],nums[i]
		}
	}
	if end == start {
		result = append(result,strconv.Itoa(start))
	} else {
		result = append(result,strconv.Itoa(start)+"->"+strconv.Itoa(end))
	}
	return result
}