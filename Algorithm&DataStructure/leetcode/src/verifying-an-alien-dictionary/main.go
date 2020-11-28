package main

import "fmt"

func main() {
	fmt.Println(isAlienSorted([]string{"hello","leetcode"},"hlabcdefgijkmnopqrstuvwxyz"))
	fmt.Println(isAlienSorted([]string{"word","world","row"},"worldabcefghijkmnpqstuvxyz"))
	fmt.Println(isAlienSorted([]string{"apple","app"},"abcdefghijklmnopqrstuvwxyz"))
}

func isAlienSorted(words []string, order string) bool {
	if len(words) < 2 {
		return true
	}
	rankMap := make(map[byte]int)
	for i := 0;i <len(order) ;i++  {
		rankMap[order[i]] = i
	}
	i := 0
	j := 1

	for j < len(words)  {
		k := 0
		for ;k < len(words[i])&&k < len(words[j]) ;k++  {
			indexI,_:=rankMap[words[i][k]]
			indexJ,_:=rankMap[words[j][k]]
			if indexI >	indexJ {
				return false
			}
			if indexI <	indexJ {
				break
			}
		}
		if k >= len(words[j]) && k < len(words[i]) {
			return false
		}
		i ++
		j ++
	}
	return true
}