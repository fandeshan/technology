package main

import "fmt"

func main() {
	//board := [][]byte{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}}
	board := [][]byte{{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}}
	solveSudoku(board)
	for i := 0 ;i < 9;i ++ {
		for j := 0 ;j < 9;j ++ {
			fmt.Print(board[i][j]-'0'," ")
		}
		fmt.Println()
	}
}

func solveSudoku(board [][]byte)  {
	fillBlank(board,0,0)
}
func fillBlank(board [][]byte,x int,y int) bool{
	valid,numMap := isValidSudoku(board,x,y)
	if !valid {
		return false
	}
	xNext,yNext := findBlank(board,x,y)
	if  xNext ==-1 || yNext == -1 {
		fmt.Println(board)
		return true
	}
	for i := 1 ;i <=9;i++ {
		if _,ok := numMap[byte(i)];ok {
			continue
		}
		board[xNext][yNext] = byte(i)+'0'
		valid := fillBlank(board,xNext,yNext)
		if valid {
			return true
		}
		board[xNext][yNext] = '.'
	}
	return false
}
func findBlank(board [][]byte,x int,y int) (i int,j int){
	i = x
	j = y
	for ; j < len(board[i]); j++ {
		if board[i][j] == '.' {
			return
		}
	}
	i++
	for ;i <len(board);i++ {
		for j=0; j < len(board[i]); j++ {
			if board[i][j] == '.' {
				return
			}
		}
	}
	return -1,-1
}
func isValidSudoku(board [][]byte,x int,y int) (bool,map[byte]int) {
	numMap1 := make(map[byte]int)
	for i := 0;i <len(board[x]) ;i ++ {
		if board[x][i] == '.' {
			continue
		}
		if _,ok := numMap1[board[x][i]];ok {
			return false,nil
		}
		numMap1[board[x][i]] = 1
	}

	numMap2 := make(map[byte]int)
	for i := 0;i <len(board) ;i++ {
		if board[i][y] == '.' {
			continue
		}
		if _,ok := numMap2[board[i][y]];ok {
			return false,nil
		}
		numMap2[board[i][y]] = 1
	}
	numMap3 := make(map[byte]int)
	startI := (x/3)*3
	startJ := (y/3)*3
	for i := startI;i < startI+3;i ++ {
		for j := startJ;j < startJ+3;j ++ {
			if board[i][j] == '.' {
				continue
			}
			if _,ok := numMap3[board[i][j]];ok {
				return false,nil
			}
			numMap3[board[i][j]] = 1
		}
	}
	for k3,_ := range numMap3{
		numMap1[k3] = 1
	}
	for k2,_ := range numMap2{
		numMap1[k2] = 1
	}
	return true,numMap1
}