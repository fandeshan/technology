package main

import "fmt"

func main() {
	fmt.Println(movingCount(2,3,1))
	fmt.Println(movingCount(2,3,0))
}

func movingCount(m int, n int, k int) int {
	arr := make([][]bool,n)
	for i := 0;i < len(arr);i++ {
		arr[i] = make([]bool,m)
	}
	result := 0
	var dfs func (x,y int)
	dfs = func(x,y int)  {
		if x < 0 || x >= n || y < 0 || y >= m {
			return
		}
		if arr[x][y] {
			return
		}
		if getSum(x,y) > k {
			return
		} else {
			result ++
			arr[x][y] = true
			dfs(x+1,y)
			dfs(x-1,y)
			dfs(x,y+1)
			dfs(x,y-1)
		}
	}
	dfs(0,0)
	return result
}


func getSum(x,y int) int {
	sum := 0
	for x >= 10 {
		sum += x%10
		x = x/10
	}
	sum += x
	for y >= 10 {
		sum += y%10
		y = y/10
	}
	sum += y
	return sum
}