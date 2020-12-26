package main

import "fmt"

func main() {
	fmt.Println(maximalRectangle([][]byte{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}))
	fmt.Println(maximalRectangle([][]byte{{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},{'1','1','0','1','1'},{'0','1','1','1','1'}}))
}

func maximalRectangle(matrix [][]byte) int {
	if len(matrix) < 1 {
		return 0
	}
	dp := make([][]int,len(matrix))
	for i := 0;i < len(matrix);i++ {
		width := 0
		dp[i] = make([]int,len(matrix[i]))
		for j := 0; j < len(matrix[i]);j++ {
			if matrix[i][j] == '1' {
				width ++
			} else {
				width = 0
			}
			dp[i][j] = width
		}
	}
	fmt.Println(dp)
	result := 0
	for i := 0;i < len(dp);i++ {
		for j := 0; j < len(dp[i]); j++ {
			x := dp[i][j]
			y := 1
			result = max(result,x*y)
			for k := i-1;k >=0;k-- {
				y++
				x = min(x,dp[k][j])
				result = max(result,x*y)
			}
		}
	}
	return result
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}
func min(a,b int) int {
	if a < b {
		return a
	}
	return b
}