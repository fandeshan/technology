package main

import "fmt"

func main() {
	fmt.Println(isValidSudoku([][]byte{{'8','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}}))
}

func isValidSudoku(board [][]byte) bool {
	for i := 0;i <len(board);i++ {
		numMap := make(map[byte]int)
		for j := 0;j <len(board[i]) ;j++ {
			if board[i][j] == '.' {
				continue
			}
			if _,ok := numMap[board[i][j]];ok {
				return false
			}
			numMap[board[i][j]] = 1
		}
	}
	for i := 0;i <len(board[0]);i++ {
		numMap := make(map[byte]int)
		for j := 0;j <len(board) ;j++ {
			if board[j][i] == '.' {
				continue
			}
			if _,ok := numMap[board[j][i]];ok {
				return false
			}
			numMap[board[j][i]] = 1
		}
	}
	for i := 0;i <len(board);i+=3 {
		for j := 0;j <len(board[i]) ;j+=3 {
			numMap := make(map[byte]int)
			for x := i;x < i+3;x++ {
				for y := j;y < j+3;y++ {
					if board[x][y] == '.' {
						continue
					}
					if _,ok := numMap[board[x][y]];ok {
						return false
					}
					numMap[board[x][y]] = 1
				}
			}
		}
	}
	return true
}