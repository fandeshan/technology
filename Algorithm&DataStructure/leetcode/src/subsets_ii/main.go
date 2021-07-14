package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(subsetsWithDup([]int{1,2,2}))
}

func subsetsWithDup(nums []int) [][]int {
	result := make([][]int,0)
	if len(nums) < 1 {
		return result
	}
	arr := []int{}
	sort.Ints(nums)
	dfs(nums,arr,&result,0)
	return result
}
func dfs(nums []int,arr []int,result *[][]int,pos int) {
	newArr := make([]int,len(arr))
	copy(newArr,arr)
	*result = append(*result,newArr)
	for i := pos;i<len(nums) ;i++  {
		if i > pos && nums[i-1]==nums[i]{
			continue
		}
		arr = append(arr,nums[i])
		dfs(nums,arr,result,i+1)
		arr = arr[:len(arr)-1]
	}
}