package main

import "fmt"

func main() {

	fmt.Println(solveNQueens(4))
}

func solveNQueens(n int) [][]string {

	result := [][]string{}
	for i := 0; i < n; i++ {
		posArr := [][]int{}
		arr := make([][]bool, n)
		for i := 0; i < len(arr); i++ {
			arr[i] = make([]bool, n)
		}
		markAndRecPos(arr, posArr, 0, i, &result)
	}
	return result
}
func markAndRecPos(arr [][]bool, posArr [][]int, x int, y int, result *[][]string) {
	// 最大可以走n-1
	n := len(arr)
	if x >= n || y >= n {
		return
	}
	newArr := make([][]bool, n)
	for i := 0; i < n; i++ {
		newArr[i] = make([]bool, n)
		copy(newArr[i], arr[i])
	}
	markTrue(newArr, x, y)
	for i := 0; i < n; i++ {
		markTrue(newArr, x-i, y)
		markTrue(newArr, x+i, y)
		markTrue(newArr, x, y+i)
		markTrue(newArr, x, y-i)
		markTrue(newArr, x-i, y-i)
		markTrue(newArr, x+i, y+i)
		markTrue(newArr, x-i, y+i)
		markTrue(newArr, x+i, y-i)
	}
	posArr = append(posArr[:], []int{x, y})
	if len(posArr) >= n {
		posMap := make(map[int]int)
		for i := 0; i < len(posArr); i++ {
			posMap[posArr[i][0]] = posArr[i][1]
		}
		puzzle := make([]string, n)
		for i := 0; i < n; i++ {
			//puzzle[i] = make([]byte,n)
			for j := 0; j < n; j++ {
				if y, ok := posMap[i]; ok && y == j {

					puzzle[i] = puzzle[i] + "Q"
				} else {
					puzzle[i] = puzzle[i] + "."
				}
			}

		}
		*result = append(*result, puzzle)
		return
	}

	if x+1 < n {
		for i := 0; i < n; i++ {
			if newArr[x+1][i] == false {
				markAndRecPos(newArr, posArr, x+1, i, result)
			}
		}
	}

}
func markTrue(newArr [][]bool, x int, y int) {
	if x >= 0 && y >= 0 && x < len(newArr) && y < len(newArr) {
		newArr[x][y] = true
	}
}
