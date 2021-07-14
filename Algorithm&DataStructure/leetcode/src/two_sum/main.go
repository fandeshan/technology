package main

import (
	"fmt"
)

func main() {
	//nums := []int{2, 7, 11, 15}
	//target := 9
	nums := []int{3,2,4}
	target := 6
	result := twoSum2(nums, target)
	fmt.Println(result)
	//fmt.Printf("[%d, %d]\n", result[0], result[1])
}

func twoSum(nums []int, target int) []int {
	result := []int{0, 0}
	lenNums := len(nums)
	findSum := false
	for i := 0; i < lenNums; i++ {
		for j := i + 1; j < lenNums; j++ {
			if nums[i]+nums[j] == target {
				result[0] = i
				result[1] = j
				findSum = true
				break
			}

		}
		if findSum {
			break
		}
	}
	return result
}

func twoSum2(nums []int, target int) []int {
	if len(nums) < 2 {
		return nil
	}
	tmpNums := make([][]int,len(nums))
	for i:=0;i<len(tmpNums) ;i++  {
		tmpNums[i] = make([]int,2)
		tmpNums[i][0] = nums[i]
		tmpNums[i][1] = i
	}
	quickSort(tmpNums)
	left := 0
	right := len(tmpNums)-1
	for left < right  {
		if tmpNums[left][0] + tmpNums[right][0] > target {
			right --
		}else if tmpNums[left][0] + tmpNums[right][0] < target {
			left ++
		}else {
			return []int{tmpNums[left][1],tmpNums[right][1]}
		}
	}
	return nil
}

func quickSort(nums [][]int) {
	if len(nums) < 2 {
		return
	}
	i := 0
	j := len(nums) - 1
	base := nums[i][0]
	base1 := nums[i][1]
	for i < j {
		for i < j && nums[j][0] >= base {
			j--
		}
		if i < j {
			nums[i][0] = nums[j][0]
			nums[i][1] = nums[j][1]
			i++
		}

		for i < j && nums[i][0] < base {
			i++
		}
		if i < j {
			nums[j][0] = nums[i][0]
			nums[j][1] = nums[i][1]
			j--
		}
	}
	nums[i][0] = base
	nums[i][1] = base1
	quickSort(nums[:i])
	quickSort(nums[i+1:])
}