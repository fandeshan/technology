package main

import "fmt"

func main() {
	fmt.Println(trap([]int{0,1,0,2,1,0,1,3,2,1,2,1}))
}

func trap(height []int) int {
	if len(height) < 3 {
		return 0
	}
	left := 0
	leftMax := height[left]
	right := len(height) - 1
	rightMax := height[right]
	sumWater := 0
	for left < right  {
		if height[left] <= height[right] {
			if height[left] < leftMax {
				sumWater += leftMax - height[left]
			}
			left ++
			if height[left] > leftMax {
				leftMax = height[left]

			}
		} else {
			if height[right] < rightMax {
				sumWater += rightMax - height[right]
			}
			right --
			if height[right] > rightMax {
				rightMax = height[right]
			}
		}
	}
	return sumWater
}