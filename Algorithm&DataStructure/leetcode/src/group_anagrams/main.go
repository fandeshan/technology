package main

import "fmt"

func main() {
	fmt.Println(isPrimiary(103))
	fmt.Println(groupAnagrams1([]string{"eat", "tea", "tan", "ate", "nat", "bat"}))
	fmt.Println(groupAnagrams1([]string{"", ""}))
}

func groupAnagrams(strs []string) [][]string {
	//repeatsMap := make(map[string]int)
	result := make([][]string,0)
	resultMaps := make([]map[byte]int,0)
	for i := 0 ; i < len(strs) ; i++ {
		//if _,ok := repeatsMap[strs[i]];ok {
		//	continue
		//}
		//repeatsMap[strs[i]] = 1
		currByteMap := make(map[byte]int)
		for j := 0;j <len(strs[i]);j ++ {
			currByteMap[strs[i][j]] ++
		}
		appended := false
		for j := 0 ; j < len(result); j++ {
			matched := true
			if len(resultMaps[j]) != len(currByteMap) {
				continue
			}
			for k1,v1 := range resultMaps[j] {
				if currByteMap[k1] != v1 {
					matched = false
					break
				}
			}
			if matched {
				appended = true
				result[j] = append(result[j],strs[i])
				break
			}
		}
		if !appended {
			result = append(result,[]string{strs[i]})
			resultMaps = append(resultMaps,currByteMap)
		}
	}
	return result
}




func groupAnagrams1(strs []string) [][]string {
	var primeList = []int{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101}

	var multi func(string) int
	multi = func(a string) int {
		multiVal :=1
		for i:=0; i<len(a); i++ {
			multiVal *= primeList[int(a[i]-'a')]
		}
		return multiVal
	}
	result := make([][]string,0)
	resultMaps := make(map[int]int,0)
	j := 0
	for i:=0; i<len(strs); i++ {
		multiVal := multi(strs[i])
		if v,ok := resultMaps[multiVal];ok {
			result[v] = append(result[v],strs[i])
		} else {
			result = append(result,[]string{strs[i]})
			resultMaps[multiVal] = j
			j ++
		}
	}
	return result
}


func isPrimiary(n int) bool{
	for i := 2; i < n ; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}