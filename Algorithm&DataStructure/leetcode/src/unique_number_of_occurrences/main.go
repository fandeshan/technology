package main

import (
	"fmt"
)

func main() {
	fmt.Println(uniqueOccurrences([]int{1, 2, 2, 1, 1, 3}))
	fmt.Println(uniqueOccurrences([]int{1, 2}))
}

func uniqueOccurrences(arr []int) bool {
	if len(arr) < 1 {
		return false
	}
	cntMap := make(map[int]int)
	for _, val := range arr {
		if cnt, ok := cntMap[val]; ok {
			cntMap[val] = cnt + 1
		} else {
			cntMap[val] = 1
		}
	}
	uniqueMap := make(map[int]int)
	for _, v := range cntMap {
		if _, ok := uniqueMap[v]; ok {
			return false
		}
		uniqueMap[v] = 1
	}
	return true
}
