package main

import "fmt"

func main() {
	fmt.Println(isPossible([]int{1,2,3,3,4,5}))
	fmt.Println(isPossible([]int{1,2,3,3,4,4,5,5}))
}

func isPossible(nums []int) bool {
	n := len(nums)
	if n < 3 {
		return false
	}
	cnts := make([]int,n + 1)
	min := nums[0]
	for i := 1; i < len(nums) ; i++  {
		index := nums[i]-min
		if index > n  {
			return false
		}
		cnts[index]++
	}
	subLen := 1

	for i := 1;i < n + 1 ;i++  {
		if cnts[i] <= cnts[i-1] {
			if subLen < 3 {
				return false
			} else {
				restart := i - subLen
				for restart < n && cnts[restart] == 0 {
					restart ++
				}
				if restart == n {
					return true
				}
				i = restart
				cnts[restart] --
				subLen = 1
			}
		} else {
			subLen ++
			cnts[i] --
		}

	}
	return true
}