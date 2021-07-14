package main

import "fmt"

func main() {
	fmt.Println(longestPalindrome("babad"))
}

func longestPalindrome(s string) string {
	n := len(s)
	if n < 2 {
		return s
	}

	partArr := make([][]bool,n)

	for i := 0; i < n ; i++  {
		partArr[i] = make([]bool,n)
		partArr[i][i] = true
		if i < n -1 && s[i+1] == s[i] {
			partArr[i][i+1] = true
		}
	}
	for i := 2; i < n ; i++  {
		for j := 0;j < n -i ; j ++  {
			if s[j] == s[j + i] && partArr[j +1][j + i -1] {
				partArr[j][j + i] = true
			}
		}
	}
	start := 0
	end := 0
	for i := n - 1;i >= 0 ;i --  {
		finded := false
		for j := 0;j < n - i ; j++  {
			if partArr[j][j+i] {
				start,end =j,j+i
				finded = true
				break
			}
		}
		if finded {
			break
		}
	}
	return s[start:end+1]
}