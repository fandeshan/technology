package main

import "fmt"

func main() {
	printResult([]int{0})
	printResult([]int{1})
	printResult([]int{0,1,0,3,12})
	printResult([]int{1,2,3,4,5})
	printResult([]int{0,0,0,0,0})
}
func printResult(nums []int)  {
	moveZeroes(nums)
	fmt.Println(nums)
}

func moveZeroes(nums []int)  {
	if len(nums) < 2 {
		return
	}
	lenNums := len(nums)
	leftZero := 0
	leftNot := 0
	for  {
		for leftZero < lenNums && nums[leftZero] != 0  {
			leftZero ++
		}
		if leftZero >= lenNums - 1 {
			return
		}
		if leftNot < leftZero + 1 {
			leftNot = leftZero + 1
		}
		for leftNot < lenNums && nums[leftNot] == 0 {
			leftNot ++
		}
		if leftNot >=  lenNums {
			return
		}
		nums[leftZero],nums[leftNot] = nums[leftNot],nums[leftZero]
		leftZero ++
	}
}