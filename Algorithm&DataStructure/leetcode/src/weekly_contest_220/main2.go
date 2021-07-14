package main

import "fmt"

func main() {
	fmt.Println(maximumUniqueSubarray([]int{5,2,1,2,5,2,1,2,5}))
	fmt.Println(maximumUniqueSubarray([]int{187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434}))
}
func maximumUniqueSubarray(nums []int) int {
	n := len(nums)
	numSums := make([]int,n + 1)
	numSums[0] = 0
	for i:=1;i < n + 1;i++ {
		numSums[i] = numSums[i-1] + nums[i-1]
	}

	tmpMap := make(map[int]int)
	left := 0
	right := 0
	result := 0
	for right < n {
		_,ok := tmpMap[nums[right]]
		for  ;right < n && !ok;{
			if _,ok = tmpMap[nums[right]];ok {
				break
			}
			tmpMap[nums[right]] = 1
			right++

		}
		if right == n {
			if result < numSums[right]-numSums[left] {
				result = numSums[right]-numSums[left]
			}
			break
		}
		if result < numSums[right]-numSums[left] {
			result = numSums[right]-numSums[left]
		}
		for nums[left] != nums[right] && left < right {
			delete(tmpMap,nums[left])
			left++
		}
		delete(tmpMap,nums[left])
		left ++
	}
	return result
}