package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(reverseWords("  "))
	fmt.Println(reverseWords(" "))
	fmt.Println(reverseWords("a good   example"))
}

func reverseWords(s string) string {
	if len(s) < 1 || s == " " {
		return ""
	}
	words := strings.Split(s," ")
	result := ""
	for i :=len(words) -1;i >= 0 ;i --  {
		if "" == words[i] {
			continue
		}
		result = result + " " + words[i]
	}
	if result == "" {
		 return result
	}
	return result[1:]
}