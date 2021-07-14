package main

import "fmt"

func main() {
	fmt.Println(numIslands([][]byte{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}))
	fmt.Println(numIslands([][]byte{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}}))
}

func numIslands(grid [][]byte) int {
	if len(grid) < 1 {
		return 0
	}
	lenGrid := len(grid)
	visited := make([][]bool,lenGrid)
	for i := 0; i < lenGrid ; i ++  {
		visited[i] = make([]bool,len(grid[i]))
	}
	rst := 0
	for i := 0; i < len(grid) ; i ++  {
		for j := 0; j < len(grid[i]) ;j ++  {
			if grid[i][j] == '1' && !visited[i][j] {
				bfs(grid,i,j,visited)
				rst ++
			}
		}
	}
	
	return rst
}
func bfs(grid [][]byte,x int ,y int,visited [][]bool)  {

	step := [][]int{{-1,0},{0,-1},{1,0},{0,1}}
	arrQueue :=[][]int{{x,y}}
	visited[x][y] = true
	for len(arrQueue) > 0  {
		lenQueue := len(arrQueue)

		for i := 0;i < lenQueue ; i++  {
			currArr := arrQueue[0]
			arrQueue = arrQueue[1:]
			for j := 0; j < 4 ; j ++  {
				newX := currArr[0] + step[j][0]
				newY := currArr[1] + step[j][1]
				if newX < 0 || newX >= len(grid) {
					continue
				}
				if newY < 0 || newY >= len(grid[0]) {
					continue
				}
				if visited[newX][newY] {
					continue
				}
				if grid[newX][newY] == '0' {
					continue
				}
				visited[newX][newY] = true
				arrQueue = append(arrQueue,[]int{newX,newY})
			}
		}
	}
}