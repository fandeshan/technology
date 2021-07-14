package main

import "fmt"

func main() {
	A := []string{"acabcddd", "bcbdbcbd", "baddbadb", "cbdddcac", "aacbcccd", "ccccddda", "cababaab", "addcaccd"}
	//A := []string{"bella","label","roller"}
	//A := []string{"cool","lock","cook"}
	//A := []string{"bbddabab","cbcddbdd","bbcadcab","dabcacad","cddcacbc","ccbdbcba","cbddaccc","accdcdbb"}

	fmt.Println(commonChars(A))
}

func commonChars(A []string) []string {
	mapCh := make(map[byte]map[int]int)
	for i := 0; i < len(A); i++ {
		for _, ch := range []byte(A[i]) {
			cntMap, ok := mapCh[ch]
			if !ok {
				cntMap = make(map[int]int)
			}
			cnt, ok2 := cntMap[i]
			if !ok2 {
				cnt = 0
			}
			cnt++
			cntMap[i] = cnt
			mapCh[ch] = cntMap
		}
	}
	result := []string{}
	for k, v := range mapCh {
		if len(v) == len(A) {
			min := 100
			for _, vc := range v {
				if vc < min {
					min = vc
				}
			}
			for i := 0; i < min; i++ {
				result = append(result, string(k))
			}
		}
	}
	return result
}
