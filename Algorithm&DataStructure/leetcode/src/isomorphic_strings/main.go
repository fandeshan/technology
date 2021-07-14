package main

import "fmt"

func main() {
	fmt.Println(isIsomorphic("egg","add"))
	fmt.Println(isIsomorphic("foo","bar"))
	fmt.Println(isIsomorphic("paper","title"))
	fmt.Println(isIsomorphic("ab","aa"))
	fmt.Println(isIsomorphic("13","42"))
}

func isIsomorphic(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	if len(s) < 1 {
		return true
	}
	sArr := make([]int,256)
	tArr := make([]int,256)
	for i := 0;i < len(s);i++ {
		if sArr[s[i]] == 0 {
			if tArr[t[i]] > 0 {
				return false
			}
			sArr[s[i]] = int(t[i])+1
			tArr[t[i]] = int(s[i])+1
		} else {
			if tArr[t[i]] != int(s[i]) +1  {
				return false
			}
		}
	}
	return true
}