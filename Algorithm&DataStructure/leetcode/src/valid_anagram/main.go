package main

import "fmt"

func main() {
	fmt.Println(isAnagram("anagram","nagaram"))
}

func isAnagram(s string, t string) bool {
	if len(s) < 1 {
		if len(t) < 1 {
			return true
		}
		return false
	}
	sMap := make(map[byte]int)
	for i := range s {
		if v,ok := sMap[s[i]];ok{
			sMap[s[i]] = v + 1
		}else{
			sMap[s[i]] = 1
		}
	}

	tMap := make(map[byte]int)
	for i := range t {
		if v,ok := tMap[t[i]];ok{
			tMap[t[i]] = v + 1
		} else {
			tMap[t[i]] = 1
		}
	}

	for sk,sv := range sMap {
		if tv,ok := tMap[sk];ok{
			if tv != sv {
				return false
			}
			delete(tMap,sk)
		} else {
			return false
		}
	}
	if len(tMap) > 0 {
		return false
	}
	return true
}