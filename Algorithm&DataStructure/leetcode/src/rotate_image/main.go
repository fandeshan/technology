package main

import "fmt"

func main() {
	//matrix := [][]int{{1,2,3},{4,5,6},{7,8,9}}
	matrix := [][]int{{5, 1, 9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}}
	fmt.Println(matrix)
	rotate(matrix)
	fmt.Println(matrix)
}

func rotate(matrix [][]int)  {
	if len(matrix) <= 1 {
		return
	}
	n := len(matrix)
	start := 0
	end := n - 1
	for end > start {
		for i := start ; i < end;i ++ {
			tmp := matrix[start][i]
			matrix[start][i] = matrix[end + start - i][start]
			matrix[end + start - i][start] = matrix[end][end + start - i]
			matrix[end][end + start - i] = matrix[i][end]
			matrix[i][end] = tmp
		}
		start ++
		end --
	}
	//for i := 0 ; i < n - 1;i ++ {
		//tmp := matrix[0][i]
		//matrix[0][i] = matrix[n-1-i][0]
		//matrix[n-1-i][0] = matrix[n-1][n-1-i]
		//matrix[n-1][n-1-i] = matrix[i][n -1]
		//matrix[i][n -1] = tmp
		//fmt.Println(matrix)
		//matrix[0][i],matrix[i][n -1],matrix[n-1][n-1-i],matrix[n-1-i][0] = matrix[n-1-i][0],matrix[0][i],matrix[i][n -1],matrix[n-1][n-1-i]
	//}
	//rotate(matrix[1:n-1][1:n-1])

}