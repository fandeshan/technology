package main

import "fmt"

func main() {
	//s := "applepenapple"
	//wordDict := []string{"apple", "pen"}
	//s := "pineapplepenapple"
	//wordDict := []string{"apple", "pen", "applepen", "pine", "pineapple"}
	s := "catsandog"
	wordDict := []string{"cats", "dog", "sand", "and", "cat"}
	fmt.Println(wordBreak(s,wordDict))
}

func wordBreak(s string, wordDict []string) bool {
	dictMap := make(map[string]int)
	for i := 0;i < len(wordDict) ;i++  {
		dictMap[wordDict[i]] = 1
	}
	canSegmentArr := make([]bool, len(s) + 1)
	canSegmentArr[0] = true
	maxWordLen := maxDictWordLen(wordDict)
	for i := 1;i <= len(s) ;i ++  {
		for j:=0;j <= maxWordLen && j <= i ;j++  {
			if !canSegmentArr[i - j] {
				continue
			}
			if _, ok := dictMap[s[i - j: i]]; ok {
				canSegmentArr[i] = true
			}
		}
	}
	//fmt.Println(canSegmentArr)
	return canSegmentArr[len(s)]
}
func maxDictWordLen(wordDict []string) int{
	max := 0
	for i := 0;i < len(wordDict) ;i++  {
		if len(wordDict[i]) > max {
			max = len(wordDict[i])
		}
	}
	return max
}