package main

import "fmt"

func main() {
	fmt.Println(groupAnagrams([]string{"eat", "tea", "tan", "ate", "nat", "bat"}))
	fmt.Println(groupAnagrams([]string{"", ""}))
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