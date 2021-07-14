package main

func main() {

}


func findBall(grid [][]int) []int {

	if len(grid) == 0 {
		return nil
	}
	result := make([]int,len(grid[0]))
	for j := 0 ;j < len(result) ;j ++ {
		i1 := 0
		j1 := j
		canDown := true
		for k := 0;k < len(grid);k++ {
			i2,j2,flag := move(i1,j1,grid)
			if !flag {
				canDown = false
				break
			}
			i1 = i2
			j1 = j2
		}
		if canDown {
			result[j] = j1
		} else {
			result[j] = -1
		}
	}
	return result
}
func move(i,j int,grid [][]int) (int,int,bool){
	if grid[i][j] == 1 {
		if j + 1 >= len(grid[0]) {
			return i,j,false
		} else if grid[i][j+1] == 1 {
			return i+1,j+1,true
		} else {
			return i,j,false
		}
	} else {
		if j -1 < 0 {
			return i,j,false
		} else if grid[i][j-1] == 1 {
			return i,j,false
		} else {
			return i+1,j-1,true
		}
	}
}