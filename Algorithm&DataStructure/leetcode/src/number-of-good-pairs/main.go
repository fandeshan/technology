package main

import "fmt"

func main() {
	fmt.Println(numIdenticalPairs([]int{1,2,3}))
}

func numIdenticalPairs(nums []int) int {
	if len(nums) < 2 {
		return 0
	}
	numsCount := make(map[int]int)
	for i := 0; i < len(nums) ;i++  {
		if num,ok := numsCount[nums[i]];ok {
			numsCount[nums[i]] = num + 1
		} else {
			numsCount[nums[i]] = 1
		}
	}
	total := 0
	for _,v := range numsCount {
		total += sumNums(v - 1)
	}
	return total
}
func sumNums(n int) int {
	if n == 0 {
		return 0
	}
	return n + sumNums(n -1)
}