package main

import "fmt"

func main() {
	fmt.Println(pivotIndex([]int{1,7,3,6,5,6}))
	fmt.Println(pivotIndex([]int{1,2,3}))
	fmt.Println(pivotIndex([]int{3,2,3}))
	fmt.Println(pivotIndex([]int{-1,-1,-1,0,1,1}))
	fmt.Println(pivotIndex([]int{1,1,0,-1,-1,-1}))
	fmt.Println(pivotIndex([]int{0,0}))
	fmt.Println(pivotIndex([]int{1}))
}

func pivotIndex(nums []int) int {
	n := len(nums)
	if n < 1 {
		return -1
	}
	sums := make([]int,n+1)
	sums[0] = 0
	for i := 1; i < n+1;i++ {
		sums[i] = sums[i-1]+nums[i-1]
	}
	if sums[n] -sums[1] == 0 {
		return 0
	}

	for i := 1;i < n-1;i++ {
		if sums[i] == sums[n]-sums[i+1] {
			return i
		}
	}
	if sums[n-1] == 0 {
		return n-1
	}
	return -1
}