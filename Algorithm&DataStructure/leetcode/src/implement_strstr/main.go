package main

import "fmt"

func main() {
	fmt.Println(strStr("hello","ll"))
	fmt.Println(strStr("aaaaa","bba"))
	fmt.Println(strStr("a","a"))
}

func strStr(haystack string, needle string) int {
	if len(needle) < 1 {
		return 0
	}
	if len(haystack) < 1 {
		return -1
	}
	n := len(needle)
	for i := 0 ; i <= len(haystack)-n ; i++ {
		if haystack[i:i+n] == needle {
			return i
		}
	}
	return -1
}