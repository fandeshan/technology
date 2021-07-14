package main

import "fmt"

func main() {
	fmt.Println(largestRectangleArea([]int{2,1,5,6,2,3}))
}

func largestRectangleArea(heights []int) int {
	if len(heights) < 1 {
		return 0
	}
	heights = append(heights,0)
	stack := make([]int,0)
	result := 0
	for i := 0 ; i < len(heights);i++ {
		for len(stack) > 0 && heights[i] < heights[stack[len(stack)-1]] {
			heightIndex := stack[len(stack) -1]
			stack = stack[:len(stack)-1]
			if len(stack) < 1 {
				result = max(result,heights[heightIndex]*i)
			} else {
				result = max(result,heights[heightIndex]*(i-stack[len(stack) -1] -1))
			}

		}
		stack = append(stack,i)
	}
	return result
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}