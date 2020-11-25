package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(arrayStringsAreEqual([]string{"ab","c"},[]string{"a","bc"}))
}
func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	str1 := strings.Builder{}
	for i :=0 ;i < len(word1) ; i++  {
		str1.WriteString(word1[i])
	}
	str2 := strings.Builder{}
	for i := 0; i < len(word2) ; i++  {
		str2.WriteString(word2[i])
	}
	return str1.String() == str2.String()
}