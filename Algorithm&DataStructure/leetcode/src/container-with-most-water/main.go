package main

import "fmt"

func main() {
	fmt.Println(maxArea([]int{1,8,6,2,5,4,8,3,7}))
	fmt.Println(maxArea([]int{1,1}))
	fmt.Println(maxArea([]int{4,3,2,1,4}))
	fmt.Println(maxArea([]int{1,2,1}))
}

func maxArea(height []int) int {
	if len(height) <2 {
		return 0
	}
	left := 0
	//leftMax := height[left]
	right := len(height) - 1
	//rightMax := height[right]
	max := min(height[left],height[right])*(right-left)
	for left < right  {
		if height[left] < height[right] {
			left ++
		} else {
			right --
		}
		currArea := min(height[left],height[right])*(right-left)
		if currArea > max {
			max = currArea
		}
	}
	return max
}
func min(a int,b int) int{
	if a <= b {
		return a
	}
	return b
}