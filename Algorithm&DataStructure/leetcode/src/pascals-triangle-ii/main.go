package main

import "fmt"

func main() {
	fmt.Println(getRow(33))
}

func getRow(rowIndex int) []int {
	result := make([]int,rowIndex+2)
	for i := 0 ;i <= rowIndex;i++ {
		start := rowIndex-i
		result[start] = 1
		for j := start+1;j < rowIndex+1;j ++ {
			result[j] = result[j] + result[j+1]
		}
	}
	return result[:rowIndex+1]
}