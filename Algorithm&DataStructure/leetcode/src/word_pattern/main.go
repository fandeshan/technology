package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(wordPattern("abba","dog cat cat dog"))
	fmt.Println(wordPattern("abba","dog cat cat fish"))
	fmt.Println(wordPattern("aaaa","dog cat cat dog"))
	fmt.Println(wordPattern("abba","dog dog dog dog"))
}

func wordPattern(pattern string, s string) bool {
	if len(pattern) < 1  {
		if len(strings.Trim(s," ")) < 1 {
			return true
		}
		return false
	}
	strs := strings.Split(strings.Trim(s," ")," ")
	if len(pattern) != len(strs) {
		return false
	}
	matchMap1 := make(map[byte]string)
	matchMap2 := make(map[string]byte)
	for i :=0;i < len(pattern);i++ {
		if val,ok := matchMap1[pattern[i]];ok {
			if val != strs[i] {
				return false
			}
		} else {
			matchMap1[pattern[i]] = strs[i]
		}
		if val,ok := matchMap2[strs[i]];ok {
			if val != pattern[i] {
				return false
			}
		} else {
			matchMap2[strs[i]] = pattern[i]
		}
	}
	return true
}