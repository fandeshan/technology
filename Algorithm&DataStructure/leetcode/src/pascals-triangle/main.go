package main

import "fmt"

func main() {
	fmt.Println(generate(5))
	fmt.Println(generate(1))
	fmt.Println(generate(2))
	fmt.Println(generate(10))
}

func generate(numRows int) [][]int {
	if numRows == 0 {
		return nil
	}
	result := make([][]int,numRows)

	for i := 0; i < numRows; i ++ {
		result[i] = make([]int,i+1)
		for j := 0 ;j < i+1; j ++ {
			if i == 0 {
				result[i][j] = 1
			} else {
				a := 0
				b := 0
				if j -1 >= 0{
					a = result[i-1][j-1]
				}
				if j < len(result[i-1]) {
					b = result[i-1][j]
				}
				result[i][j] = a + b
			}
		}
	}
	return result
}