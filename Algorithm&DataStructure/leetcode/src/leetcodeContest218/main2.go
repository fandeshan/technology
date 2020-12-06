package main

import "fmt"

func main() {
	fmt.Println(maxOperations([]int{1,2,3,4},5))
	fmt.Println(maxOperations([]int{3,1,3,4,3},6))
	fmt.Println(maxOperations([]int{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4},2))
}
func maxOperations(nums []int, k int) int {
	if len(nums) < 2 {
		return 0
	}
	numsMap := make(map[int]int)
	for i := 0 ; i < len(nums) ; i++ {
		numsMap[nums[i]] ++
	}
	result := 0
	//fmt.Println(numsMap)
	for kNums,cnt := range numsMap{
		if cnt < 1 {
			continue
		}
		cnt1,ok := numsMap[k-kNums]
		if !ok {
			continue
		}
		if 2 * kNums == k && cnt1 < 2 {
			continue
		}
		if kNums == k - kNums {
			for numsMap[kNums]> 1 {
				numsMap[kNums] --
				numsMap[k-kNums] --
				result ++
			}
		}else {
			for numsMap[kNums]>0 && numsMap[k-kNums] > 0 {
				numsMap[kNums] --
				numsMap[k-kNums] --
				result ++
			}
		}

	}
	return result
}