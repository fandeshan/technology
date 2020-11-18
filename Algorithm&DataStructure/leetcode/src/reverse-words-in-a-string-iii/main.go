package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(reverseWords("Let's take LeetCode contest"))
}

func reverseWords(s string) string {
	if len(s) < 1 || s == " " {
		return ""
	}
	words := strings.Split(s," ")
	result := ""
	for i:=0;i<len(words) ;i++  {
		lenWord := len(words[i])
		newWordByte := make([]byte,lenWord)
		for j :=lenWord -1;j >= 0 ;j --  {
			newWordByte[lenWord-1-j] = words[i][j]
		}
		result += " " + string(newWordByte)
	}
	return result[1:]
}