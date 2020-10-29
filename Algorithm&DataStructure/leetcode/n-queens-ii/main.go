package main

import "fmt"

func main() {
	//arr := make([][]int,4)
	//for i := 0;i< len(arr) ; i++  {
	//	arr[i] = make([]int,4)
	//	for j :=0;j<len(arr[i]) ;j++  {
	//		arr[i][j] =1
	//	}
	//}
	//newArr := make([][]int,4)
	//for i := 0;i< len(arr) ; i++  {
	//	newArr[i] = make([]int,4)
	//	copy(newArr[i],arr[i])
	//}
	//fmt.Println(newArr)
	fmt.Println(totalNQueens(8))
}

func totalNQueens(n int) int {

	count := 0
	for i := 0; i < n; i++ {
		posArr := [][]int{}
		arr := make([][]bool, n)
		for i := 0; i < len(arr); i++ {
			arr[i] = make([]bool, n)
		}
		markAndRecPos(arr, posArr, 0, i, &count)
	}
	return count
}
func markAndRecPos(arr [][]bool, posArr [][]int, x int, y int, count *int) {
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
		*count++
		return
	}

	if x+1 < n {
		for i := 0; i < n; i++ {
			if newArr[x+1][i] == false {
				markAndRecPos(newArr, posArr, x+1, i, count)
			}
		}
	}

}
func markTrue(newArr [][]bool, x int, y int) {
	if x >= 0 && y >= 0 && x < len(newArr) && y < len(newArr) {
		newArr[x][y] = true
	}
}
