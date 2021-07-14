package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(relativeSortArray([]int{2,3,1,3,2,4,6,7,9,2,19},[]int{2,1,4,3,9,6}))
}

func relativeSortArray(arr1 []int, arr2 []int) []int {
	if len(arr2) < 1 || len(arr1) < 1 {
		return arr1
	}
	numMap := make(map[int][]int)
	for i := 0;i < len(arr1) ;i ++  {
		numArr,ok := numMap[arr1[i]]
		if ok {
			numMap[arr1[i]] = append(numArr,arr1[i])
		}else {
			numMap[arr1[i]] = []int{arr1[i]}
		}
	}
	result :=make([]int,0)
	for i:=0;i<len(arr2) ;i++  {
		result = append(result,numMap[arr2[i]]...)
		delete(numMap,arr2[i])
	}
	if len(numMap) < 1 {
		return result
	}
	lenTmp := len(result)
	for _,v := range numMap{
		result = append(result,v...)
	}
	sort.Ints(result[lenTmp:])
	return result
}
