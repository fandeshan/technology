package main

import (
	"fmt"
	"sort"
)

func main() {
	//fmt.Println(combinationSum([]int{2,3,6,7},7))
	//fmt.Println(combinationSum([]int{2,3,5},8))
	//fmt.Println(combinationSum([]int{1},2))
	//fmt.Println(combinationSum([]int{2,7,6,3,5,1},9))
	fmt.Println(combinationSum([]int{1,2,3,5,6,7},9))
}

func combinationSum(candidates []int, target int) [][]int {
	if len(candidates) < 1 {
		return nil
	}
	result := [][]int{}
	if len(candidates) ==1 {
		for j := 1 ; j*candidates[0] <= target ; j ++ {
			if j* candidates[0] == target {
				tmp := []int{}
				for k := 0 ; k <j ; k ++ {
					tmp = append(tmp,candidates[0])
				}
				result = append(result,tmp)
				return result
			}
		}
		return nil
	}
	sort.Ints(candidates)
	for i := 0; i < len(candidates);i ++ {
		for j := 1 ; j*candidates[i] <= target ; j ++ {
			tmp := []int{}
			for k := 0 ; k <j ; k ++ {
				tmp = append(tmp,candidates[i])
			}
			if j*candidates[i] == target {
				result = append(result,tmp)
				continue
			}
			sonResult := combinationSum(candidates[i+1:],target-j*candidates[i])
			if sonResult != nil {
				for k := 0;k <len(sonResult);k++ {
					newTemp := append([]int{},tmp...)
					newTemp = append(newTemp,sonResult[k]...)
					result = append(result,newTemp)
				}
			}

		}
	}
	return result
}
//[2,7,6,3,5,1]
//9
//[[2,7],[2,7,6,1],[2,7,6,1,3,1,1,1,1],[2,7,6,1,3,1,1,1,1,3,3,1],[2,7,6,1,3,1,1,1,1,3,3,1,5,1,1],[2,7,6,1,3,1,1,1,1,3,3,1,5,1,1,1,1,1,1,1,1,1],[2,2,3,1,1],[2,2,3,1,1,5],[2,2,3,1,1,5,1,1,1,1,1],[2,2,2,3],[2,2,2,3,1,1,1],[2,2,2,2,1],[7,1,1],[6,3],[6,3,1,1,1],[3,5,1],[3,5,1,1,1,1,1,1,1],[3,3,1,1,1],[3,3,3],[5,1,1,1,1],[1,1,1,1,1,1,1,1,1]]
//[[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,2],[1,1,1,1,1,1,3],[1,1,1,1,1,2,2],[1,1,1,1,2,3],[1,1,1,1,5],[1,1,1,2,2,2],[1,1,1,3,3],[1,1,1,6],[1,1,2,2,3],[1,1,2,5],[1,1,7],[1,2,2,2,2],[1,2,3,3],[1,2,6],[1,3,5],[2,2,2,3],[2,2,5],[2,7],[3,3,3],[3,6]]