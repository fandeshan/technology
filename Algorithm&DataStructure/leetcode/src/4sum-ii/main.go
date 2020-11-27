package main

import "fmt"

func main() {
	fmt.Println(fourSumCount([]int{1, 2},[]int{-2,-1},[]int{-1,2},[]int{0,2}))
}

func fourSumCount(A []int, B []int, C []int, D []int) int {
	if len(A) < 1 {
		return 0
	}
	totalAB := [][]int{A,B}
	totalCD := [][]int{C,D}

	result :=0
	tmpSum :=0
	abMap := make(map[int]int)
	dfs(0,totalAB,tmpSum,abMap)
	tmpSum =0
	cdMap := make(map[int]int)
	dfs(0,totalCD,tmpSum,cdMap)
	for kab,vab := range abMap {
		if vcd,ok :=cdMap[-kab];ok {
			result += vab*vcd
		}
	}
	return result
}
func dfs(pos int,total [][]int,tmpSum int,sumMap map[int]int){
	if pos == len(total) {
		if cnt,ok := sumMap[tmpSum];ok {
			sumMap[tmpSum]  = cnt + 1
		}else {
			sumMap[tmpSum]  = 1
		}
		return
	}
	for i := 0;i<len(total[pos]) ;i++  {
		tmpSum += total[pos][i]
		dfs(pos+1,total,tmpSum,sumMap)
		tmpSum -= total[pos][i]
	}
}
