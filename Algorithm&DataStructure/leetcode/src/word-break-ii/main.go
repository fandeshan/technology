package main

import (
	"fmt"
	"time"
)

func main() {
	//s := "applepenapple"
	//wordDict := []string{"apple", "pen"}
	//s := "pineapplepenapple"
	//wordDict := []string{"apple", "pen", "applepen", "pine", "pineapple"}
	//s := "catsandog"
	//wordDict := []string{"cats", "dog", "sand", "and", "cat"}
	s := "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	wordDict := []string{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"}
	result := wordBreak(s,wordDict)
	for _,s := range result {
		fmt.Println(s)
	}

}

func wordBreak(s string, wordDict []string) []string {
	t1 := time.Now()
	dictMap := make(map[string]int)
	for i := 0;i < len(wordDict) ;i++  {
		dictMap[wordDict[i]] = 1
	}
	canSegmentArr := make([]bool, len(s) + 1)
	canSegmentArr[0] = true
	segmentPosArr := make([][]int,len(s))
	maxWordLen := maxDictWordLen(wordDict)
	for i := 1;i <= len(s) ;i ++  {
		for j:=0;j <= maxWordLen && j <= i ;j++  {
			if !canSegmentArr[i - j] {
				continue
			}
			if _, ok := dictMap[s[i - j: i]]; ok {
				canSegmentArr[i] = true
				if segmentPosArr[i - j] == nil {
					segmentPosArr[i - j] = make([]int,0)
				}
				segmentPosArr[i - j] = append(segmentPosArr[i - j],i)
			}
		}
	}

	fmt.Println(time.Since(t1))
	fmt.Println(segmentPosArr)
	if ! canSegmentArr[len(s)] {
		return nil
	}
	t2 := time.Now()
	result := make([]string,0)
	traversePosArr(0,segmentPosArr,[][]int{},&result,s)
	fmt.Println(time.Since(t2))
	return result
}

func traversePosArr(index int,segmentPosArr [][]int,resultArr [][]int,result *[]string,s string)  {
	if index == len(segmentPosArr) {
		singleRet := s[resultArr[0][0]:resultArr[0][1]]
		for j := 1; j < len(resultArr) ; j++  {
			singleRet = singleRet + " " + s[resultArr[j][0]:resultArr[j][1]]
		}
		*result = append(*result,singleRet)
		return
	}

	for i := 0; i < len(segmentPosArr[index]) ;i ++  {
		resultArr = append(resultArr,[]int{index,segmentPosArr[index][i]})
		traversePosArr(segmentPosArr[index][i],segmentPosArr,resultArr,result,s)
		resultArr = resultArr[0:len(resultArr)-1]
	}
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