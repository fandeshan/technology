package main

func main() {

}

func exist(board [][]byte, word string) bool {
	if len(word) < 1 {
		return true
	}
	for i := 0;i < len(board);i++ {
		for j := 0; j < len(board[i]);j++ {
			if board[i][j] == word[0] {
				tmp := board[i][j]
				board[i][j] = 0
				if checkLetter(board,i+1,j,word[1:])||
					checkLetter(board,i-1,j,word[1:])||
					checkLetter(board,i,j-1,word[1:])||
					checkLetter(board,i,j+1,word[1:]) {
					return true
				}
				board[i][j] = tmp
			}
		}
	}
	return false
}
func checkLetter(board [][]byte,i,j int, word string) bool {
	if len(word) < 1 {
		return true
	}
	if i < 0 || i >= len(board) {
		return false
	}
	if j < 0 || j >= len(board[i]){
		return false
	}
	if board[i][j] == word[0] {
		tmp := board[i][j]
		board[i][j] = 0
		result := checkLetter(board,i+1,j,word[1:])||
			checkLetter(board,i-1,j,word[1:])||
			checkLetter(board,i,j-1,word[1:])||
			checkLetter(board,i,j+1,word[1:])
		board[i][j] = tmp
		return result
	}
	return false
}