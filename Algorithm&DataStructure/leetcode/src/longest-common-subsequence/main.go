package main

import "fmt"

func main() {
	fmt.Println(longestCommonSubsequence("bsbininm","jmjkbkjkv"))
}

func longestCommonSubsequence(text1 string, text2 string) int {
	if len(text1) < 1 || len(text2) < 1 {
		return 0
	}
	result := make([][]int,len(text2)+1)
	result[0] = make([]int,len(text1) + 1)
	for i := 1; i <= len(text2) ; i++  {
		result[i] = make([]int,len(text1)+1)
		for j := 1; j <= len(text1) ; j++  {
			if text1[j - 1] == text2[i - 1] {
				result[i][j] = result[i-1][j-1] + 1
			}else{
				result[i][j] = max(result[i-1][j],result[i][j-1])
			}
		}
	}
	return result[len(text2)][len(text1)]
}
func max(a int,b int) int {
	if a >= b {
		return a
	}
	return b
}