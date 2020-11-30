package main

import "fmt"

func main() {
	fmt.Println(removeElement([]int{2,3,2,3},3))
	fmt.Println(removeElement([]int{0,1,2,2,3,0,4,2},2))
	fmt.Println(removeElement([]int{4,5},4))
}

func removeElement(nums []int, val int) int {
	n := len(nums)
	if n < 1 {
		return 0
	}
	if n == 1 {
		if nums[0] == val {
			return 0
		}
		return 1
	}
	i := 0
	j := 1
	for j < n  {
		for i < n && nums[i] != val  {
			i ++
		}
		j = i + 1
		for j < n && nums[j] == val {
			j ++
		}
		//fmt.Println(i,j)
		if j < n {
			nums[i],nums[j] = nums[j],nums[i]
			i ++
			j ++

		}
		//fmt.Println(nums)


	}
	return i
}