package main

import "fmt"

func main() {
	fmt.Println(printNumbers(3))
}
func printNumbers(n int) []int {
	result := []int{}
	maxVal := 1
	for i := 0; i < n; i++ {
		maxVal = maxVal * 10
	}
	for i := 1; i < maxVal; i++ {
		result = append(result, i)
	}
	return result
}
