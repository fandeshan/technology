package main

import "fmt"

func main() {
	fmt.Println(countConsistentStrings("cad",[]string{"cc","acd","b","ba","bac","bad","ac","d"}))
}

func countConsistentStrings(allowed string, words []string) int {
	if len(words) < 1 {
		return 0
	}
	allowMap := make(map[byte]int)
	for i :=0; i < len(allowed);i ++ {
		allowMap[allowed[i]] = 1
	}
	result := 0
	for i :=0; i < len(words);i ++ {
		//checkMap := make(map[byte]int)
		same := true
		for j := 0;j < len(words[i]);j++ {
			if _,ok := allowMap[words[i][j]];!ok {
				same = false
				break
			}

		}
		if same {
			result ++
		}
	}
	return result
}
