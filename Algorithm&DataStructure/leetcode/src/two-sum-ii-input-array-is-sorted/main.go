package main

import "fmt"

func main() {
	fmt.Println(twoSum([]int{2, 7, 11, 15},9))
}

func twoSum(numbers []int, target int) []int {
	if len(numbers) < 2 {
		return nil
	}
	left := 0
	right := len(numbers)-1
	for left < right  {
		if numbers[left] + numbers[right] > target {
			right --
		}else if numbers[left] + numbers[right] < target {
			left ++
		}else {
				return []int{left+1,right+1}
		}
	}
	return nil
}