package main

import "fmt"

func main() {
	fmt.Println(threeSum([]int{-1,0,1,2,-1,-4}))
}

func threeSum(nums []int) [][]int {
	if len(nums) < 3 {
		return nil
	}
	quickSort(nums)
	result := make([][]int,0)
	for i:=0;i<len(nums)-2 ;i++  {
		if i>0 && nums[i] == nums[i-1] {
			continue
		}
		left := i + 1
		right := len(nums)-1
		for left < right {
			if nums[i] + nums[left] + nums[right] == 0 {
				result = append(result,[]int{nums[i],nums[left],nums[right]})
				right--
				left++
				for left < right && nums[left] == nums[left-1]  {
					left ++
				}
				for left < right && nums[right] == nums[right+1]  {
					right --
				}
			}else if nums[i] + nums[left] + nums[right] < 0 {
				left ++
			}else {
				right--
			}
		}
	}
	return result
}

func quickSort(nums []int) {
	if len(nums) < 2 {
		return
	}
	i := 0
	j := len(nums) - 1
	base := nums[i]
	base1 := nums[i]
	for i < j {
		for i < j && nums[j] >= base {
			j--
		}
		if i < j {
			nums[i] = nums[j]
			i++
		}

		for i < j && nums[i] < base {
			i++
		}
		if i < j {
			nums[j] = nums[i]
			j--
		}
	}
	nums[i] = base
	nums[i]= base1
	quickSort(nums[:i])
	quickSort(nums[i+1:])
}