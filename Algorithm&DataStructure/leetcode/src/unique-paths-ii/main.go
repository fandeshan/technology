package main

import "fmt"

func main() {
	fmt.Println(uniquePathsWithObstacles([][]int{{0,0,0},{0,1,0},{0,0,0}}))
	fmt.Println(uniquePathsWithObstacles([][]int{{1,0}}))
}

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	if len(obstacleGrid) < 1 || len(obstacleGrid[0]) < 1 {
		return 0
	}
	paths := make([][]int,len(obstacleGrid))
	for i := 0; i < len(obstacleGrid) ; i++  {
		paths[i] = make([]int,len(obstacleGrid[i]))
		for j := 0; j < len(obstacleGrid[i]) ; j++  {
			if obstacleGrid[i][j] == 1 {
				paths[i][j] = 0
				continue
			}
			if i == 0 && j == 0 {
				paths[0][0] = 1
			} else if i == 0 {
				paths[i][j] = paths[i][j-1]
			} else if j == 0 {
				paths[i][j] = paths[i-1][j]
			} else {
				paths[i][j] = paths[i-1][j] + paths[i][j-1]
			}

		}
	}
	return paths[len(paths)-1][len(paths[0]) -1]
}