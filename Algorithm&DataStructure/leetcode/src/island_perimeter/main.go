package main

import "fmt"

func main(){
	fmt.Println(islandPerimeter([][]int{{0,1,0,0},
	{1,1,1,0},
	{0,1,0,0},
	{1,1,0,0}}))
}

func islandPerimeter(grid [][]int) int {
	if len(grid)<1 {
		return 0
	}
	sum := 0
	for i:=0;i<len(grid);i++  {
		for j:=0;j<len(grid[i]) ;j++  {
			if grid[i][j] > 0 {
				if i==0 {
					sum ++
				} else if grid[i-1][j] == 0 {
					sum ++
				}
				if j==0 {
					sum ++
				} else if grid[i][j-1] == 0 {
						sum ++
				}
				if i== len(grid) -1 {
					sum ++
				} else if grid[i+1][j] == 0 {
						sum ++
				}
				if j == len(grid[i])-1 {
					sum ++
				}else if grid[i][j+1] == 0{
						sum ++
				}
				
			}
		}
	}
	return sum
}
