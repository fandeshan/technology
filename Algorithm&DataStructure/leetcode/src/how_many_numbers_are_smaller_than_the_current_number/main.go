package main

import "fmt"

func main() {
	//arr := []int{72,6,57,88,60,42,83,73,48,85}
	//arr := []int{1,2,3,4,5,6}
	//arr := [][]int{{6,0},{5,1},{4,2},{3,3},{2,4}}
	//quickSort(arr)
	//fmt.Print(arr)
	//nums := []int{8,1,2,2,3}
	//nums := []int{1,1,1,1}
	//nums := []int{1,2,3,4}
	nums := []int{6, 5, 4, 8}
	fmt.Println(smallerNumbersThanCurrent(nums))
}

func smallerNumbersThanCurrent(nums []int) []int {
	if len(nums) < 1 {
		return []int{}
	}
	numsLen := len(nums)
	nums2Arr := make([][]int, numsLen)
	for i := 0; i < numsLen; i++ {
		nums2Arr[i] = make([]int, 2)
		nums2Arr[i][0] = nums[i]
		nums2Arr[i][1] = i
	}
	quickSort(nums2Arr)
	result := make([]int, numsLen)
	for i := 0; i < numsLen; i++ {
		count := i
		for k := i - 1; k >= 0; k-- {
			if nums2Arr[k][0] == nums2Arr[i][0] {
				count--
			} else {
				break
			}
		}
		result[nums2Arr[i][1]] = count
	}
	return result
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
