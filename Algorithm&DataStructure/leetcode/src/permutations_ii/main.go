package main

import (
	"fmt"
	"sort"
)

func main() {
	//fmt.Println(permuteUnique([]int{1,1,2}))
	//fmt.Println(permuteUnique([]int{1,2,3}))
	fmt.Println(permuteUnique([]int{-1,-1,-1,1,1,2,2,2}))
	//[[-1,-1,-1,1,1,2,2,2],[-1,-1,-1,1,2,1,2,2],[-1,-1,-1,1,2,2,1,2],[-1,-1,-1,1,2,2,2,1],[-1,-1,-1,2,1,1,2,2],[-1,-1,-1,2,1,2,1,2],[-1,-1,-1,2,1,2,2,1],[-1,-1,-1,2,2,1,1,2],[-1,-1,-1,2,2,1,2,1],[-1,-1,-1,2,2,2,1,1],[-1,-1,1,-1,1,2,2,2],[-1,-1,1,-1,2,1,2,2],[-1,-1,1,-1,2,2,1,2],[-1,-1,1,-1,2,2,2,1],[-1,-1,1,1,-1,2,2,2],[-1,-1,1,1,2,-1,2,2],[-1,-1,1,1,2,2,-1,2],[-1,-1,1,1,2,2,2,-1],[-1,-1,1,2,-1,1,2,2],[-1,-1,1,2,-1,2,1,2],[-1,-1,1,2,-1,2,2,1],[-1,-1,1,2,1,-1,2,2],[-1,-1,1,2,1,2,-1,2],[-1,-1,1,2,1,2,2,-1],[-1,-1,1,2,2,-1,1,2],[-1,-1,1,2,2,-1,2,1],[-1,-1,1,2,2,1,-1,2],[-1,-1,1,2,2,1,2,-1],[-1,-1,1,2,2,2,-1,1],[-1,-1,1,2,2,2,1,-1],[-1,-1,2,-1,1,1,2,2],[-1,-1,2,-1,1,2,1,2],[-1,-1,2,-1,1,2,2,1],[-1,-1,2,-1,2,1,1,2],[-1,-1,2,-1,2,1,2,1],[-1,-1,2,-1,2,2,1,1],[-1,-1,2,1,-1,1,2,2],[-1,-1,2,1,-1,2,1,2],[-1,-1,2,1,-1,2,2,1],[-1,-1,2,1,1,-1,2,2],[-1,-1,2,1,1,2,-1,2],[-1,-1,2,1,1,2,2,-1],[-1,-1,2,1,2,-1,1,2],[-1,-1,2,1,2,-1,2,1],[-1,-1,2,1,2,1,-1,2],[-1,-1,2,1,2,1,2,-1],[-1,-1,2,1,2,2,-1,1],[-1,-1,2,1,2...
}

func permuteUnique(nums []int) [][]int {
	sort.Ints(nums)
	result := make([][]int,0)
	var dfs func(map[int]int, []int)
	dfs = func(travelMap map[int]int,single []int) {
		if len(single) == len(nums) {
			tmp := make([]int,len(single))
			copy(tmp,single)
			result = append(result,tmp)
			return
		}
		repeatMap := make(map[int]int)
		for i := 0 ; i < len(nums) ; i++ {
			if _,ok2:=repeatMap[nums[i]] ; ok2 {
				continue
			}
			if _,ok:=travelMap[i] ; ok {
				continue
			}
			travelMap[i] = 1
			repeatMap[nums[i]] = 1
			dfs(travelMap,append(single,nums[i]))
			delete(travelMap,i)
		}
	}
	travelMapOrigin := make(map[int]int)
	single := make([]int,0)
	dfs(travelMapOrigin,single)
	return result
}