package main

import "fmt"

func main() {
	//nums := []int{1, 5, 11, 5}
	//nums := []int{1, 2, 3, 5}
	//nums := []int{2, 7, 6, 5}
	//nums := []int{1,1}
	//nums := []int{1,5,11,3}
	//nums := []int{1,2,3,4,5,6,7}
	//nums := []int{14,9,8,4,3,2}
	nums := []int{2, 2, 3, 5}
	fmt.Println(canPartition(nums))
}

func canPartition(nums []int) bool {
	lenNums := len(nums)
	sumResult := 0
	for i := 0; i < lenNums; i++ {
		sumResult = sumResult + nums[i]
	}
	if sumResult%2 != 0 {
		return false
	}
	resultArr := make([][]int, lenNums)
	for i := 0; i < lenNums; i++ {
		resultArr[i] = make([]int, lenNums)

		for j := 0; j < lenNums; j++ {
			if i == j {
				if i > 0 {
					resultArr[i][j] = resultArr[i-1][j]
				}
				continue
			} else if i == 0 {
				resultArr[i][j] = nums[i] + nums[j]
			} else {
				resultArr[i][j] = resultArr[i-1][j] + nums[i]
			}
			if resultArr[i][j] == sumResult/2 {
				return true
			}
			for k := 0; k < i; k++ {
				if resultArr[k][j]+nums[i] == sumResult/2 {
					return true
				}
			}

		}

	}
	return false
}
