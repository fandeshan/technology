package main

import "fmt"

func main() {
	fmt.Println(minCut("aab"))
	fmt.Println(minCut("aabbac"))
}

func minCut(s string) int {
	n := len(s)
	if n < 2 {
		return 0
	}
	partArr := make([][]bool,n)
	for i := 0; i < n ; i++  {
		partArr[i] = make([]bool,n)
		partArr[i][i] = true
		if i < n -1 && s[i+1] == s[i] {
			partArr[i][i+1] = true
		}
	}
	//partArr的p[i][j] i<j 表示的是i到j之间是回文
	//eg : aabbac p[1][4]=true 说明字符串下标1-4是回文，即abba是回文
	for i := 2; i < n ; i++  {
		for j := 0;j < n -i ; j ++  {
			if s[j] == s[j + i] && partArr[j +1][j + i -1] {
				partArr[j][j + i] = true
			}
		}
	}

	minCuts := make([]int,n + 1)
	for i := 0; i < len(minCuts) ;i++  {
		minCuts[i] = i - 1
	}
	for i := 1; i <= len(partArr) ;i++  {
		for j :=0;j < i ;j++  {
			// 如果j 到i-i之间是回文，则该中间不用分割，则j之前的最小再加一次 和 当前最小取最小值
			if partArr[j][i-1] {
				minCuts[i] = min(minCuts[j]+1,minCuts[i])
			}
		}
	}
	return minCuts[n]
}
func min(a int,b int) int {
	if a < b {
		return a
	}
	return b
}