package main

import "fmt"

func main() {
	fmt.Println(longestCommonPrefix([]string{"flower","flow","flight"}))
	fmt.Println(longestCommonPrefix([]string{"","flow","flight"}))
	fmt.Println(longestCommonPrefix([]string{"dog","racecar","car"}))
}

func longestCommonPrefix(strs []string) string {
	if len(strs) < 1 {
		return ""
	}
	if len(strs[0]) < 1 {
		return ""
	}
	result := ""
	j := 0
	for {
		add := true
		if j >= len(strs[0]) {
			break
		}
		for i:=1;i < len(strs) ;i++  {
			if j < len(strs[i]) && strs[0][j] ==  strs[i][j] {
				
			}else{
				add = false
				break;
			}
		}
		if !add {
			break
		}
		result += string(strs[0][j])
		j ++
	}
	return result
}