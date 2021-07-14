package main

import "fmt"

func main() {
	fmt.Println(firstUniqChar("leetcode"))
	fmt.Println(firstUniqChar("loveleetcode"))
}

func firstUniqChar(s string) int {
	n := len(s)
	cnt := make([]int,26)
	for i := n-1;i >= 0;i -- {
		cnt[s[i]-'a']++
	}
	for i := 0;i < n ;i ++ {
		if cnt[s[i]-'a'] == 1 {
			return i
		}
	}
	return -1
}