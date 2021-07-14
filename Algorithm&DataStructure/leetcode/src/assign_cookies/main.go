package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(findContentChildren([]int{1,2,3},[]int{1,1}))
	fmt.Println(findContentChildren([]int{1,2},[]int{1,2,3}))
}

func findContentChildren(g []int, s []int) int {
	if len(g) < 1 {
		return 0
	}
	sort.Ints(g)
	sort.Ints(s)
	result := 0
	j := 0
	for i := 0 ; i < len(g) ; i++ {
		for j < len(s) && s[j] < g[i] {
			j++
		}
		if j >= len(s) {
			break
		}
		result ++
		j ++
	}
	return result
}