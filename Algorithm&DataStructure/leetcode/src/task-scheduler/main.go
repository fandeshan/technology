package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(leastInterval([]byte{'A','A','A','A','A','A','B','C','D','E','F','G'},2))
	fmt.Println(leastInterval([]byte{'A','A','A','B','B','B'},0))
	fmt.Println(leastInterval([]byte{'A','A','A','B','B','B'},2))
	fmt.Println(leastInterval([]byte{'A','A','A','B','B','B'},50))
}

func leastInterval(tasks []byte, n int) int {
	cnts := make([]int,26)
	for i := 0; i < len(tasks) ; i++ {
		cnts[tasks[i]-'A']++
	}
	result := 0
	for  {
		sort.Sort(sort.Reverse(sort.IntSlice(cnts)))
		if cnts[0] == 0 {
			break
		}
		for j := 0 ; j <= n; j++ {
			if j > 25 {
				result ++
				continue
			}
			if cnts[j] > 0 {
				cnts[j] --
			} else if cnts[0] == 0 {
				break
			}
			result ++
		}
	}
	return result
}