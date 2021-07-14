package main

import "fmt"

func main() {
	fmt.Println(exchange([]int{1,2,3,4}))
	fmt.Println(exchange([]int{1,2,4,5,3,6}))
	fmt.Println(exchange([]int{1}))
	fmt.Println(exchange([]int{2}))
	fmt.Println(exchange([]int{}))
}

func exchange(nums []int) []int {
	if len(nums) < 1 {
		return nil
	}
	lenNums := len(nums)
	oddIndex := 0
	evenIndex := 0
	i := 0
	for  oddIndex >= evenIndex || i< lenNums  {
		for ;i<lenNums ;i++  {
			if nums[i] % 2 == 0 {
				evenIndex = i
				i ++
				break
			}
		}
		j:=i
		for ;j<lenNums;j++ {
			if nums[j] % 2 != 0 {
				oddIndex = j
				break
			}
		}
		if j >= lenNums{
			break
		}
		if oddIndex > evenIndex {
			nums[oddIndex],nums[evenIndex] = nums[evenIndex],nums[oddIndex]
		}
	}
	return nums
}