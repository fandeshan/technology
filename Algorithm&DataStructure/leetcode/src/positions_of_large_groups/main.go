package main

import "fmt"

func main() {
	fmt.Println(largeGroupPositions("abcdddeeeeaabbbcd"))
	fmt.Println(largeGroupPositions("aba"))
	fmt.Println(largeGroupPositions("abc"))
	fmt.Println(largeGroupPositions("abbxxxxzzy"))
}

func largeGroupPositions(s string) [][]int {
	n := len(s)
	if n < 1 {
		return nil
	}
	left := 0
	right := 0
	result := make([][]int,0)
	for {
		for right < n && s[right] == s[left] {
			right ++
		}
		if right - left > 2 {
			result = append(result,[]int{left,right-1})
		}
		if right >= n {
			break
		}
		left = right
	}
	return result
}