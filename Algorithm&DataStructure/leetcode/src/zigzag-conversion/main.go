package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(convert("LEETCODEISHIRING",3))
	fmt.Println(convert("LEETCODEISHIRING",4))
	fmt.Println(convert("ABCDE",4))
	fmt.Println(convert("ABCD",4))
	fmt.Println(convert("PAYPALISHIRING",9))
}

func convert(s string, numRows int) string {
	n := len(s)
	if n < 2 || numRows < 2 {
		return s
	}
	halfZ := numRows + numRows -2
	workStep := (numRows - 2) + 1
	col := (n/halfZ + 1) * workStep
	row := numRows
	retBytes := make([][]byte,row)
	for i := 0; i < row ; i++  {
		retBytes[i] = make([]byte,col)
	}
	k := 0

	for i := 0; i < col ;i += workStep  {
		for j := 0;j < row  && k <len(s) ; j ++  {
			retBytes[j][i] = s[k]
			k ++
		}
		for l := 1; l <= numRows -2 && k <len(s) ; l++  {
			retBytes[row-l-1][i+l] = s[k]
			k ++
		}
	}
	for i := 0; i < len(retBytes) ;i++  {
		for j := 0; j <len(retBytes[i]) ;j++  {
			if retBytes[i][j] != 0 {
				fmt.Print(string(retBytes[i][j]))
			}else {
				fmt.Print(" ")
			}
		}
		fmt.Println()
	}
	sb := strings.Builder{}
	for i := 0; i < row ; i++  {
		for j := 0; j < col ; j++  {
			if retBytes[i][j] != 0 {
				sb.WriteByte(retBytes[i][j])
			}
		}
	}
	return sb.String()
}