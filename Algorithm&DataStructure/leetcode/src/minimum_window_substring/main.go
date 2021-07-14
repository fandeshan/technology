package main

import "fmt"

func main() {
	fmt.Println(minWindow("ADOBECODEBANC","ABC"))
	fmt.Println(minWindow("a","a"))
	fmt.Println(minWindow("bba","ba"))
	fmt.Println(minWindow("aa","aa"))
}

func minWindow(s string, t string) string {
	left := 0
	right := 0
	neededMap := make(map[byte]int)
	windowMap := make(map[byte]int)
	for i := 0; i < len(t) ; i++  {
		neededMap[t[i]]++
	}
	result := ""
	matchCnt := 0
	for right < len(s)  {
		if cnt,ok := neededMap[s[right]]; ok {
			windowMap[s[right]] ++
			if windowMap[s[right]] == cnt {
				matchCnt ++
			}
		}
		right ++
		for matchCnt == len(neededMap) && left < right  {
			//fmt.Println(left,right)
			//fmt.Println(windowMap)
			tmp :=s[left:right]
			if result == "" {
				result = tmp
			}else if right-left < len(result) {
				result = tmp
			}
			if _,ok := windowMap[s[left]]; ok {
				windowMap[s[left]] --
				if windowMap[s[left]] < neededMap[s[left]] {
					matchCnt --
				}

			}
			left ++
		}
	}
	return result
}